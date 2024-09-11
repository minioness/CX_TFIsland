-- Artist 데이터 생성
INSERT INTO artist (artist_id, artist_name, color, company, logo, score)
VALUES (1, 'Artist A', 'FF5733', 'Company A', 'https://linkto.logo1.com', 0),
       (2, 'Artist B', '00FF00', 'Company B', 'https://linkto.logo2.com', 0);

-- Address 데이터 생성 (Assuming Address is another entity with address_id)
INSERT INTO address (address_id, city, district, street)
VALUES (1, '서울특별시', '관악구', '봉천동'),
       (2, '대구광역시', '북구', '매천동');

-- CaseUser 데이터 생성
INSERT INTO case_user (case_user_id, nickname, brightness, color, point, profile_link, theme_artist_artist_id, address_id)
VALUES (1, 'User1', 0, 'FFFFFF', 0, 'https://profile1.com', 1, 1),
       (2, 'User2', 0, 'AAAAAA', 0, 'https://profile2.com', 2, 2);

-- Memory 데이터 생성
INSERT INTO memory (memory_id, created_at, title, content, color, memory_date, created_by_case_user_id)
VALUES (1, NOW(), 'First Memory', 'This is the content of the first memory', 'FFFFFF', '2024-09-10', 1),
       (2, NOW(), 'Second Memory', 'This is the content of the second memory', 'FF5733', '2024-09-11', 2);

-- Mission 데이터 생성
INSERT INTO mission (mission_id, reward, title, description, artist_artist_id)
VALUES (1, 1000, 'First Mission', 'Complete the first mission', 1),
       (2, 2000, 'Second Mission', 'Complete the second mission', 2);

-- ArtistSchedule 데이터 생성
INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (1, 'CONCERT', 'First Concert', '2024-09-12 19:00:00', 37.7749, -122.4194, 1),
       (2, 'FAN_SIGN', 'First Fan Meeting', '2024-09-13 14:00:00', 40.7128, -74.0060, 2);

-- UserSchedule 데이터 생성
INSERT INTO user_schedule (user_schedule_id, category, title, scheduled_at, latitude, longitude, user_case_user_id)
VALUES (1, 'BIRTHDAY', 'User1 Schedule', '2024-09-12 10:00:00', 34.0522, -118.2437, 1),
       (2, 'BIRTHDAY_CAFE', 'User2 Schedule', '2024-09-14 16:00:00', 51.5074, -0.1278, 2);

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
