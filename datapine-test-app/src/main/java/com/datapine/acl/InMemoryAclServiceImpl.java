package com.datapine.acl;

import com.datapine.domain.User;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 *  The simplest possible implementation of AclService interface.
 *  Uses in-memory collection of ACLs, providing fast and easy access to them.
 * (based on material: http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html)
 *
 * Created by Dmitrii on 9/9/15.
 */
@Service
public class InMemoryAclServiceImpl implements AclService {

    Map<ObjectIdentity, Acl> acls = new HashMap<ObjectIdentity, Acl>();

    @PostConstruct
    public void initializeACLs() {
        // create ACLs according to existing predefined users
        ObjectIdentity user1 = new ObjectIdentityImpl(User.class, "admin@admin.com");
        ObjectIdentity user2 = new ObjectIdentityImpl(User.class, "user@user.com");
        ObjectIdentity user3 = new ObjectIdentityImpl(User.class, "acl@acl.com");

        Acl acl1 = new SimpleAclImpl(user1, new ArrayList<AccessControlEntry>(1));
        acl1.getEntries().add(0, new AccessControlEntryImpl("ace1", acl1, new PrincipalSid("admin@admin.com"), ExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl1.getObjectIdentity(), acl1);
        Acl acl2 = new SimpleAclImpl(user2,  new ArrayList<AccessControlEntry>(1));
        acl2.getEntries().add(0, new AccessControlEntryImpl("ace2", acl2, new PrincipalSid("user@user.com"), ExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl2.getObjectIdentity(), acl2);
        Acl acl3 = new SimpleAclImpl(user3,  new ArrayList<AccessControlEntry>(1));
        acl3.getEntries().add(0, new AccessControlEntryImpl("ace3", acl3, new PrincipalSid("wrong@sid.com"), ExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl3.getObjectIdentity(), acl3);
    }

    public List<ObjectIdentity> findChildren(ObjectIdentity parentIdentity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Acl readAclById(ObjectIdentity object, List<Sid> sids) throws NotFoundException {
        Map<ObjectIdentity, Acl> map = readAclsById(Arrays.asList(object), sids);
        Assert.isTrue(map.containsKey(object), "There should have been an Acl entry for ObjectIdentity " + object);

        return map.get(object);
    }

    public Acl readAclById(ObjectIdentity object) throws NotFoundException {
        return readAclById(object, null);
    }

    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects) throws NotFoundException {
        return readAclsById(objects, null);
    }

    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects, List<Sid> sids) throws NotFoundException {
        Map<ObjectIdentity, Acl> result = new HashMap<ObjectIdentity, Acl>();

        for (ObjectIdentity object : objects) {
            if (acls.containsKey(object)) {
                result.put(object, acls.get(object));
            } else {
                throw new NotFoundException("Unable to find ACL information for object identity '" + object.toString() + "'");
            }
        }

        return result;
    }
}
