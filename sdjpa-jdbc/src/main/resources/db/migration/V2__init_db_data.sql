insert into author (first_name, last_name) values ('Kuriboh', 'YuGiOh');

insert into book (isbn, publisher, title, author_id) values ('123-1234567890', 'Meepo1 W33haa',
                                                             'Guide to Pro Dota2, 2th Edition',
                                                             (select id from author where first_name = 'Kuriboh' and last_name = 'YuGiOh'));

insert into book (isbn, publisher, title, author_id) values ('123-1234567891', 'Meepo1 W33haa',
                                                             'How to win Ti, 1st Edition',
                                                             (select id from author where first_name = 'Kuriboh' and last_name = 'YuGiOh') );

insert into book (isbn, publisher, title, author_id) values ('123-1234567892', 'Meepo1 W33haa',
                                                             '5 Steps to Deep Focus, 6th Edition',
                                                             (select id from author where first_name = 'Kuriboh' and last_name = 'YuGiOh') );

insert into author (first_name, last_name) values ('Pudgy', 'Dendi');

insert into book (isbn, publisher, title, author_id) values ('123-1234567893', 'Meepo2 W33haa',
                                                             'All You Need to Know of New Patch',
                                                             (select id from author where first_name = 'Pudgy' and last_name = 'Dendi') );

insert into author (first_name, last_name) values ('Invoker', 'Miracle');

insert into book (isbn, publisher, title, author_id) values ('123-1234567894', 'Meepo2 W33haa',
                                                             '5 Steps to Win Mid',
                                                             (select id from author where first_name = 'Invoker' and last_name = 'Miracle') );