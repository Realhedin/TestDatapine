package com.datapine.acl;

import com.datapine.domain.User;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;

/** overwrite the strategy: build ObjectIdentity based on user object login property,
 *  instead of Spring Security default getId() call
 *  Created by Dmitrii on 9/9/15.
 */
public class UserNameRetrievalStrategy implements ObjectIdentityRetrievalStrategy {

    public ObjectIdentity getObjectIdentity(Object domainObject) {
        User user = (User) domainObject;
        return new ObjectIdentityImpl(User.class, user.getEmail());
    }

}
