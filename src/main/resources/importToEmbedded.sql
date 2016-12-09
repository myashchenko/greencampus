INSERT INTO PUBLIC.users (id, email, password, role, name, created_date) VALUES (1, 'email1@email.com', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_TEACHER', 'Name Name1', CURRENT_TIMESTAMP);
INSERT INTO PUBLIC.users (id, email, password, role, name, created_date) VALUES (2, 'email2@email.com', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_UNACTIVE', 'Name Name2', CURRENT_TIMESTAMP);
INSERT INTO PUBLIC.users (id, email, password, role, name, created_date) VALUES (3, 'email3@email.com', 'pass123', 'ROLE_UNACTIVE', 'Name Name3', CURRENT_TIMESTAMP);
INSERT INTO PUBLIC.users (id, email, password, role, name, created_date) VALUES (4, 'email4@email.com', 'pass123', 'ROLE_UNACTIVE', 'Name Name4', CURRENT_TIMESTAMP);
INSERT INTO PUBLIC.users (id, email, password, role, name, created_date) VALUES (5, 'admin@admin', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_ADMIN', 'Admin', CURRENT_TIMESTAMP);

INSERT INTO public.chat_dialog (id, created_date, updated_date) VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO public.chat_dialog (id, created_date, updated_date) VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 2);

INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (2, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (2, 3);

INSERT INTO public.chat_message (id, chat_dialog_id, user_from, text, created_date) VALUES (1, 1, 2, 'test text', CURRENT_TIMESTAMP);
INSERT INTO public.dialog_unread_messages (chatdialog_id, unread_count, user_id) VALUES (1, 1, 1);

INSERT INTO public.chat_message (id, chat_dialog_id, user_from, text, created_date) VALUES (2, 2, 3, 'test text', CURRENT_TIMESTAMP);
INSERT INTO public.dialog_unread_messages (chatdialog_id, unread_count, user_id) VALUES (2, 1, 1);

insert into public.course (id, title, description, created_date) values (1, 'Java Programming course', 'Java Core, Multithreading, Java EE', CURRENT_TIMESTAMP);
insert into public.course (id, title, description, created_date) values (2, 'FrontEnd course', 'HTML, CSS, JavaScript', CURRENT_TIMESTAMP);
insert into public.course (id, title, description, created_date) values (3, 'Scala course', 'Functional programming', CURRENT_TIMESTAMP);

insert into public.user_course (id, user_id, course_id, role, created_date) values (1, 1, 1, 'CREATOR', CURRENT_TIMESTAMP);
insert into public.user_course (id, user_id, course_id, role, created_date) values (2, 1, 2, 'CREATOR', CURRENT_TIMESTAMP);
insert into public.user_course (id, user_id, course_id, role, created_date) values (3, 1, 3, 'CREATOR', CURRENT_TIMESTAMP);

insert into public.course_theme (id, title, course_id, created_date) values (1, 'Theme1', 1, CURRENT_TIMESTAMP);
insert into public.course_theme (id, title, course_id, created_date) values (2, 'Theme2', 1, CURRENT_TIMESTAMP);
insert into public.course_theme (id, title, course_id, created_date) values (3, 'Theme3', 1, CURRENT_TIMESTAMP);