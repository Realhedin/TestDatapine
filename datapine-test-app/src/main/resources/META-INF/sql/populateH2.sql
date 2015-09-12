--
-- Fill USERS table
--
insert into users  values (1,'user@user.com','user');
insert into users  values (2,'admin@admin.com','admin');
insert into users  values (3,'acl@acl.com','acl');
insert into users  values (4,'test@test.com','test');


--
-- Fill ITEMS table
--
insert into items values (1,'laptop',1);
insert into items values (2,'key',1);
insert into items values (3,'mouse',1);
insert into items values (4, 'mouse',2);
insert into items values (5, 'monitor',2);
insert into items values (6,'key',2);
insert into items values (7, 'cord',3);
insert into items values (8, 'key',3);
insert into items values (9, 'usb',3);
insert into items values (10, 'invisibleKey',4);





--
-- Fill ACL tables:
-- table ACL_SID
--
INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, '1', 'user@user.com'),
  (2, '1', 'admin@admin.com'),
  (3, '1', 'acl@acl.com'),
  (4, '1','test@test.com');

--
-- table ACL_CLASS
--
INSERT INTO acl_class (id, class) VALUES
  (1, 'com.datapine.domain.Item');


--
-- table ACL_OBJECT_IDENTITY
--

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
  (1, 1, 1, NULL, 1, '0'),
  (2, 1, 2, NULL, 1, '0'),
  (3, 1, 3, NULL, 1, '0'),
  (4, 1, 4, NULL, 1, '0'),
  (5, 1, 5, NULL, 1, '0'),
  (6, 1, 6, NULL, 1, '0'),
  (7, 1, 7, NULL, 1, '0'),
  (8, 1, 8, NULL, 1, '0'),
  (9, 1, 9, NULL, 1, '0');
--   (10,1,10, NULL, 1, '0');

--
-- table ACL_ENTRY
-- Admin - READ/WRITE, User - READ/WRITE, Acl - READ, test - nothing
--
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
  (1, 1, 1, 1, 1, '1', '1', '1'),
  (2, 2, 1, 1, 1, '1', '1', '1'),
  (3, 3, 1, 1, 1, '1', '1', '1'),
  (4, 1, 1, 1, 2, '1', '1', '1'),
  (5, 2, 1, 1, 2, '1', '1', '1'),
  (6, 3, 1, 1, 2, '1', '1', '1'),
  (7, 4, 1, 2, 1, '1', '1', '1'),
  (8, 5, 1, 2, 1, '1', '1', '1'),
  (9, 6, 1, 2, 1, '1', '1', '1'),
  (10, 4, 1, 2, 2, '1', '1', '1'),
  (11, 5, 1, 2, 2, '1', '1', '1'),
  (12, 6, 1, 2, 2, '1', '1', '1'),
  (13, 7, 1, 3, 1, '1', '1', '1'),
  (14, 8, 1, 3, 1, '1', '1', '1'),
  (15, 9, 1, 3, 1, '1', '1', '1');
