package com.datapine.acl;

import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.model.*;

import java.util.List;

/**
 * Very simple implementation of Acl interface, based on
 * org.springframework.security.acls.domain.AclImpl source (mainly the isGranted
 * method code). This implementation neither use owner concept nor parent concept.
 * (based on material:http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html)
 *
 * Created by Dmitrii on 9/9/15.
 */
public class SimpleAclImpl implements Acl {

    private static final long serialVersionUID = 1L;
    private ObjectIdentity oi;
    private List<AccessControlEntry> aces;
    private transient AuditLogger auditLogger = new ConsoleAuditLogger();

    public SimpleAclImpl(ObjectIdentity oi, List<AccessControlEntry> aces) {
        this.oi = oi;
        this.aces = aces;
    }

    public List<AccessControlEntry> getEntries() {
        return aces;
    }

    public ObjectIdentity getObjectIdentity() {
        return oi;
    }

    public Sid getOwner() {
        return null; // don't use owner concept
    }

    public Acl getParentAcl() {
        return null; // don't use inheritance
    }

    public boolean isEntriesInheriting() {
        return false; // don't use inheritance
    }

    @Override
    public boolean isSidLoaded(List<Sid> sids) {
        return true;  //always loaded
    }

    @Override
    public boolean isGranted(List<Permission> permission, List<Sid> sids, boolean administrativeMode) throws NotFoundException {

        AccessControlEntry firstRejection = null;

        for (Permission aPermission : permission) {
            for (Sid sid : sids) {
                // Attempt to find exact match for this permission mask and SID
                boolean scanNextSid = true;

                for (AccessControlEntry ace1 : aces) {

                    if ((ace1.getPermission().getMask() == aPermission.getMask()) && ace1.getSid().equals(sid)) {
                        // Found a matching ACE, so its authorization decision
                        // will prevail
                        if (ace1.isGranting()) {
                            // Success
                            if (!administrativeMode) {
                                auditLogger.logIfNeeded(true, ace1);
                            }

                            return true;
                        } else {
                            // Failure for this permission, so stop search
                            // We will see if they have a different permission
                            // (this permission is 100% rejected for this SID)
                            if (firstRejection == null) {
                                // Store first rejection for auditing reasons
                                firstRejection = ace1;
                            }

                            scanNextSid = false; // helps break the loop

                            break; // exit "aces" loop
                        }
                    }
                }

                if (!scanNextSid) {
                    break; // exit SID for loop (now try next permission)
                }
            }
        }

        if (firstRejection != null) {
            // We found an ACE to reject the request at this point, as no
            // other ACEs were found that granted a different permission
            if (!administrativeMode) {
                auditLogger.logIfNeeded(false, firstRejection);
            }

            return false;
        }

        throw new NotFoundException("Unable to locate a matching ACE for passed permissions and SIDs");
    }

}
