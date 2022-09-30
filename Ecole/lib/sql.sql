CREATE TABLE Region (
    id serial primary key,
    nom varchar(15)
);

CREATE TABLE Ecole (
    id serial primary key,
    nom varchar(15),
    idRegion integer
);
alter table ecole add FOREIGN key(idRegion) REFERENCES Region(id);

create table genre (
    id serial primary key,
    nom varchar(7)
);

create table Etudiant (
    id serial primary key,
    nom varchar(30),
    prenom varchar(30),
    idGenre integer,
    idEcole integer,
    dateNaissance date not null
);
alter table Etudiant add FOREIGN key(idGenre) REFERENCES genre(id);
alter table Etudiant add FOREIGN key(idEcole) REFERENCES Ecole(id);

create table Matiere (
    id serial primary key,
    nom varchar(15)
);

create table Note(
    id serial primary key,
    idEtudiant integer,
    idMatiere integer,
    val decimal(4,2),
    unique (idEtudiant,idMatiere)
);
alter table Note add FOREIGN key(idEtudiant) REFERENCES Etudiant(id);
alter table Note add FOREIGN key(idMatiere) REFERENCES Matiere(id);


CREATE or replace view v_Region_etudiants as 
    SELECT ec.idregion,
        count(e.id) etudiant_count
    FROM Etudiant e left join Ecole ec
    on e.idEcole=ec.id
    left join Region r 
    on r.id=ec.idregion
    GROUP BY ec.idregion;