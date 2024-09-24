/*Удаление всех пользователей и всех их данных*/
TRUNCATE public.user_data CASCADE;
/*Сброс счетчиков первичных ключей*/
ALTER SEQUENCE category_id_seq RESTART WITH 1;
ALTER SEQUENCE priority_id_seq RESTART WITH 1;
ALTER SEQUENCE stat_id_seq RESTART WITH 1;
ALTER SEQUENCE task_id_seq RESTART WITH 1;
ALTER SEQUENCE user_data_id_seq RESTART WITH 1;
ALTER SEQUENCE user_role_id_seq RESTART WITH 1;
ALTER SEQUENCE activity_id_seq RESTART WITH 1;
/*Вставка данных*/  
DO $GEN_DATA$          --создаем код с именем GEN_DATA для автоматического заполнения таблицы
BEGIN                   --границы кода
FOR i IN 1..10000 LOOP  --цикл от 1 до 10000, i счетчик итераци, LOOP это начало цикла
RAISE NOTICE 'Добавлена строка номер: %', i;    --вывод в консоль, что строка добавлена
--выполнить sql запрос который находится между $$, вметсо $1 будет подставлятся i, счетчик цикла
-- gen_random_uuid () эта встроеная функция для генерации уникльного идентификатора
EXECUTE $$ INSERT INTO public.user_data(email, username, userpassword) VALUES (
'email' || $1 || '@gmail.com', 'username' || $1, gen_random_uuid ()) returning id; $$
USING i;   -- это для того что-бы i мы могли использовать вместо $1
END LOOP; --конец цикла
END;
$GEN_DATA$
