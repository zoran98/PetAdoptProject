INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');

              
INSERT INTO kategorije (id, naziv) VALUES (1, 'Pas');
INSERT INTO kategorije (id, naziv) VALUES (2, 'Macka');
INSERT INTO kategorije (id, naziv) VALUES (3, 'Hrcak');

INSERT INTO ljubimci (id, ime, starost, udomljen, vakcinisan, pol, tezina, opis, kategorija_id) 
	VALUES (1, 'Rex', 25, false, true, 'muski', 1.5, 'Rasan pas, dobra pasmina', 1);
INSERT INTO ljubimci (id, ime, starost, udomljen, vakcinisan, pol, tezina, opis, kategorija_id) 
	VALUES (2, 'Milka', 12, false, false, 'zenski', 0.5, 'Rasna macka, izdresirana', 2);
INSERT INTO ljubimci (id, ime, starost, udomljen, vakcinisan, pol, tezina, opis, kategorija_id) 
	VALUES (3, 'Miks', 5, false, false, 'muski', 0.2, 'Obican kucni hrcak, bele boje', 3);