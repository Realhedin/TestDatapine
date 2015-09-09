package com.datapine.acl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

/**
 * Adds ACCEPT permission to standard set of Spring Security permissions.
 * Based on BasePermission code.
 * (based on material:http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html)
 *
 * Created by Dmitrii on 9/9/15.
 */
public class ExtendedPermission extends BasePermission {

    private static final long serialVersionUID = 1L;

    public static final Permission ACCEPT = new ExtendedPermission(1 << 5, 'a'); // 32


    //constructor
    private ExtendedPermission(int mask, char code) {
        super(mask, code);
    }


}
