INSERT INTO PUBLIC.users (id, email, password, role) VALUES (1, 'email1', 'pass123', 'UNACTIVE');
INSERT INTO PUBLIC.users (id, email, password, role) VALUES (2, 'email2', 'pass123', 'UNACTIVE');
INSERT INTO PUBLIC.users (id, email, password, role, name) VALUES (3, 'email3', 'pass123', 'UNACTIVE', 'third user');
INSERT INTO PUBLIC.users (id, email, password, role, name) VALUES (4, 'email4', 'pass123', 'UNACTIVE', 'fourth user');

INSERT INTO public.chat_dialog (id, dialog_name) VALUES (1, 'Test Dialog');
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 2);
INSERT INTO public.chat_message (id, chat_dialog_id, user_from, text, send_date) VALUES (1, 1, 2, 'test text', CURRENT_TIMESTAMP);

INSERT INTO public.chat_dialog (id) VALUES (2);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 1);
INSERT INTO public.chat_dialog_users (chat_dialog_id, user_id) VALUES (1, 2);