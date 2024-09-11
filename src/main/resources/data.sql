-- Artist 데이터 삽입
INSERT INTO artist (artist_id, artist_name, color, company, logo, score)
VALUES (1, 'Artist 1', 'FF5733', 'Company A', 'https://linkto.logo1.com', 0);

INSERT INTO artist (artist_id, artist_name, color, company, logo, score)
VALUES (2, 'Artist 2', '33FF57', 'Company B', 'https://linkto.logo2.com', 0);

-- ArtistSchedule 데이터 삽입 (Artist 1의 스케줄 3개)
INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (1, 'CONCERT', 'Artist 1 Concert in Seoul', '2024-09-15T18:00:00', 37.5665, 126.9780, 1);

INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (2, 'FAN_SIGN', 'Artist 1 Fan Sign Event', '2024-10-01T15:00:00', 37.5128, 127.1025, 1);

INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (3, 'BIRTHDAY', 'Artist 1 Birthday', '2024-09-20T20:00:00', 37.5530, 126.9375, 1);

-- ArtistSchedule 데이터 삽입 (Artist 2의 스케줄 3개)
INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (4, 'CONCERT', 'Artist 2 Concert in Busan', '2024-09-25T18:00:00', 35.1796, 129.0756, 2);

INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (5, 'FAN_SIGN', 'Artist 2 Fan Sign', '2024-10-05T15:00:00', 35.1028, 129.0403, 2);

INSERT INTO artist_schedule (artist_schedule_id, category, title, scheduled_at, latitude, longitude, artist_artist_id)
VALUES (6, 'BIRTHDAY', 'Artist 2 Birthday', '2024-09-30T21:00:00', 35.1796, 129.0756, 2);
