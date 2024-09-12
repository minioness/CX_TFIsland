-- Artist 데이터 생성
INSERT INTO artist (artist_id, artist_name, color, company, logo, score)
VALUES (1, '세븐틴', '92A8D1', '하이브', 'https://i.namu.wiki/i/ND9XXkVFeQQCFAQ-yauKFKgLvMrMDDrZakRf8VaKxyDEYKfgwm6i1BhyBwKOgUxVDSOYf28UhZxvpbivd2SyCg.svg', 0),
       (2, '뉴진스', 'B5E3F5', '어도어', 'https://ext.fmkorea.com/files/attach/new3/20230315/5665468/3501791154/5585681431/780d335e61c0019eae1299d856b926d5.png', 0),
       (3, '아이유', 'E4E724','이담 엔터테인먼트', 'https://i.namu.wiki/i/R0AhIJhNi8fkU2Al72pglkrT8QenAaCJd1as-d_iY6MC8nub1iI5VzIqzJlLa-1uzZm--TkB-KHFiT-P-t7bEg.webp', 0);

-- Address 데이터 생성 (Assuming Address is another entity with address_id)
INSERT INTO address (address_id, city, district, street)
VALUES (1, '서울특별시', '관악구', '봉천동'),
       (2, '대구광역시', '북구', '침산동');

-- CaseUser 데이터 생성
INSERT INTO case_user (case_user_id, nickname, brightness, color, point, profile_link, theme_artist_artist_id, address_id, intro)
VALUES (1, '김주원', 0, '92A8D1', 0, 'https://i.pinimg.com/550x/e8/ea/8c/e8ea8c212171e668b2253bc1613fb0c3.jpg', 1, 1, '쿱스 사랑해♥♥♥ 캐럿봉 아껴'),
       (2, '이창윤', 0, 'B5E3F5', 0, 'https://ext.fmkorea.com/files/attach/new3/20230315/5665468/3501791154/5585681431/780d335e61c0019eae1299d856b926d5.png', 2, 2,'뉴진스 사랑해♥♥♥');

-- Memory 데이터 생성
INSERT INTO memory (memory_id, created_at, title, content, color, memory_date, created_by_case_user_id)
VALUES (1, NOW(), '세븐틴 첫 콘서트', '대박대박대박대박 너무좋아 진짜좋아 어떡해 우리 오빠들 너무 멋져', 'FF0000', '2024-09-01', 1),
       (2, NOW(), '에스쿱스 생카', '연남동에 있는 우리 쿱스 생일카페에 처음 갔는데 이쁜 굿즈가 진짜 많아서 다 사고싶었어', '2C2C54', '2024-09-11', 1);

-- Mission 데이터 생성
INSERT INTO mission (mission_id, reward, title, description, artist_artist_id)
VALUES (1, 1000, 'First Mission', 'Complete the first mission', 1),
       (2, 2000, 'Second Mission', 'Complete the second mission', 2);

-- ArtistSchedule 데이터 생성
INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (1, 'CONCERT', 'SEVENTEEN WORLD TOUR 고양', '2024-10-04 19:00:00', 37.67640943, 126.74309496, 1),
       (2, 'FAN_SIGN', '세븐틴 10th 미니앨범 FML 팬 사인회', '2024-09-23 14:00:00', 37.62645941, 127.3213401, 1),
       (3, 'BIRTHDAY', '에스쿱스 생일', '2024-09-13 14:00:00', 37.5637591, 126.92397051, 1);

-- UserSchedule 데이터 생성
INSERT INTO user_schedule (user_schedule_id, category, title, scheduled_at, latitude, longitude, user_case_user_id)
VALUES (1, 'CONCERT', '월드투어 콘서트 가기', '2024-10-04 19:00:00', 37.67640943, 126.74309496, 1),
       (2, 'BIRTHDAY_CAFE', '쿱스 생일카페 가기', '2024-09-16 12:00:00', 37.5637591, 126.92397051, 1);

-- Image 데이터 생성 (optional, assuming Image is another entity with a case_user relationship)
INSERT INTO image (image_id, image_url, case_user_id)
VALUES (1, 'https://image1.com', 1),
       (2, 'https://image2.com', 2);

-- Music 데이터 생성
INSERT INTO music (music_id, title, artist_artist_id, case_user_id)
VALUES (1, 'Music1', 1, 1),
       (2, 'Music2', 2, 2);

-- Video 데이터 생성
INSERT INTO video (video_id, title, video_url, case_user_id)
VALUES (1, 'Video1', 'https://video1.com', 1),
       (2, 'Video2', 'https://video2.com', 2);
