ALTER TABLE course ADD COLUMN course_text tsvector;
CREATE INDEX course_text_gin ON course USING gin(course_text);
DROP TRIGGER IF EXISTS ts_course_text ON course;
CREATE TRIGGER ts_course_text BEFORE INSERT OR UPDATE ON course FOR EACH ROW EXECUTE PROCEDURE tsvector_update_trigger(course_text, 'pg_catalog.english', title, description);

INSERT INTO PUBLIC.users (id,email,password,role, name) VALUES (1, 'email1@email.com', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_TEACHER', 'Name Name1');
INSERT INTO PUBLIC.users (id,email,password,role, name) VALUES (2, 'email2@email.com', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_UNACTIVE', 'Name Name2');
INSERT INTO PUBLIC.users (id,email,password,role, name) VALUES (3, 'email3@email.com', 'pass123', 'ROLE_UNACTIVE', 'Name Name3');
INSERT INTO PUBLIC.users (id,email,password,role, name) VALUES (4, 'email4@email.com', 'pass123', 'ROLE_UNACTIVE', 'Name Name4');
INSERT INTO PUBLIC.users (id,email,password,role, name) VALUES (5, 'admin@admin', '$2a$10$0b2AErbUuyqDQ/Fw.P7Mz..dRa/cZlnWfzwCCsko8SSIs/qgMXGCa', 'ROLE_ADMIN', 'Admin');


INSERT INTO public.chat_dialog (id, update_date) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO public.chat_dialog (id, update_date) VALUES (2, CURRENT_TIMESTAMP);

INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 2);

INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (2, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (2, 3);

INSERT INTO public.chat_message (id, chat_dialog_id, user_from, text, send_date) VALUES (1, 1, 2, 'test text', CURRENT_TIMESTAMP);
INSERT INTO public.dialog_unread_messages (chatdialog_id, unread_count, user_id) VALUES (1, 1, 1);

INSERT INTO public.chat_message (id, chat_dialog_id, user_from, text, send_date) VALUES (2, 2, 3, 'test text', CURRENT_TIMESTAMP);
INSERT INTO public.dialog_unread_messages (chatdialog_id, unread_count, user_id) VALUES (2, 1, 1);

insert into public.course (id, title, description) values (1, 'Java Programming course', 'Java Core, Multithreading, Java EE');
insert into public.course (id, title, description) values (2, 'FrontEnd course', 'HTML, CSS, JavaScript');
insert into public.course (id, title, description) values (3, 'Scala course', 'Functional programming');

insert into public.user_course (id, user_id, course_id, role) values (1, 1, 1, 'CREATOR');
insert into public.user_course (id, user_id, course_id, role) values (2, 1, 2, 'CREATOR');
insert into public.user_course (id, user_id, course_id, role) values (3, 1, 3, 'CREATOR');

insert into public.course_theme (id, title, course_id) values (1, 'Theme1', 1);
insert into public.course_theme (id, title, course_id) values (2, 'Theme2', 1);
insert into public.course_theme (id, title, course_id) values (3, 'Theme3', 1);


ALTER SEQUENCE "user_seq" RESTART WITH 6;
ALTER SEQUENCE "course_seq" RESTART WITH 4;
ALTER SEQUENCE "user_course_seq" RESTART WITH 4;
ALTER SEQUENCE "chat_message_seq" RESTART WITH 3;
ALTER SEQUENCE "chat_dialog_seq" RESTART WITH 3;