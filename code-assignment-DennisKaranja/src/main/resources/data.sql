insert into post (id, title, content) values
    (1, 'Welcome', 'Welcome to this assignment'),
    (2, 'Assignment', 'Implement the missing pieces'),
    (3, 'Task', 'Add support for comment on a post');
insert into comment (id, post_id, comment) values
    (4, 1, 'Kilroy was here'),
    (5, 1, 'Foobar too'),
    (6, 1, ''),
    (7, 1, 'Foobar too Available');
