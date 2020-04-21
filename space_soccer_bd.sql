create table Cargo (IdCargo int Primary key not null, 
					NomeCargo varchar(30) not null, 
					Sigla varchar(30)  not null);


create table Cartao (idCartao int primary key, 
					 CorCartao varchar(10) not null,
					 NomeCartao varchar (10) not null );


create table Clube (IdClube int  primary key,
					AnoFundacao datetime not null, 
					CidadeFundacao varchar(40) not null,
					Logotipo image not null,
					Presidente varchar(40) not null,

					-- chave estrangeira
					EquipaTecnica int,
					Estadio int,
					Publico int );


create table EquipaTecnica (CC int Primary key not null, 
							Email varchar(40) not null unique, 
							IdCargo int  not null,
							Idade int not null, 
							Nome varchar(40) not null );


create table Estadio (IdEstadio int primary key, 
					  Cidade varchar(30) not null, 
					  Historia text not null,
					  Nome varchar(30) not null,
					  NumLugares int not null );


create table Estatisticas (IdEstatistica int primary key, 
					       Equipa1 varchar(30) not null, 
						   Equipa2 varchar(30) not null,
						   Golos int not null, 
						   NumIncidencias int not null, 
						   Possedebola int not null, 
						   RematesABaliza int not null, 
						   RematesTotais int not null );


create table Incidencia (idIncidencia  int primary key,
						 Jogador int not null,
						 TipoIncidencia int not null,

						-- chave estrangeira
						 Cartao int );


create table Jogadores (Email varchar(40) Primary Key not null,
                        Altura int not null,
						CodAcesso  int not null,
						Idade int not null,
						Nome varchar(40) not null,
						NivelAcesso int not null,
                        NumCamisola int not null,
						Posicao int not null,

						-- chave estrangeira
						Clube int ,
 						Incidencias int,
                        Nacionalidade int not null,
						Peso int );


create table Jogo (IdJogo int primary key,
				   Arbitro varchar(40) not null, 
				   DataJogo datetime not null,
				   SegundoArbitro varchar(40), 
				   VideoArbitro varchar(40),

				-- chave estrangeira
				   Estatistica int,
				   Incidencias int,
				   TempoJogo int,
				   Torneio int );


create table Nacionalidade (IdNacionalidade int primary key ,
							NomeNacionalidade varchar(30) not null,
							Sigla varchar(2) not null );


create table Posicao (IDPosicao int primary key,
					  NomePosicao varchar(30) not null,
					  SiglaPosicao varchar(2) not null );


create table Publico (IdPublico int primary key,
					  DataNascimento datetime not null,
					  Email varchar(50) unique not null,
					  ImagemPerfil int not null,
					  NivelAcesso int not null,
					  PalavraPasse varchar(30) not null,
					  Username varchar(50) unique not null );


create table TipoIncidencia (idTipoIncidencia int primary key, 
							 Incidencia int,
							 TipoIncidencia varchar(30) not null,
							 Minuto int not null );


create table Torneio (IdTorneio int primary key, 
					  Ano datetime not null,
					  Campeao varchar(30), 
					  Nome varchar(40) not null, 
					  NumEquipas int not null,
					  
					  -- chave estrangeira
					  Clubes int );





alter table Clube add Constraint FKEQUIPATECNICA Foreign Key (EquipaTecnica) references EquipaTecnica (CC);
alter table Clube add Constraint FKESTADIO Foreign Key (Estadio) references Estadio (IdEstadio);
alter table Clube add Constraint FKPUBLICO Foreign Key (Publico) references Publico (IdPublico);

alter table EquipaTecnica add constraint FKEQUIPATECNICAFUNCAO Foreign key (IdCargo) references Cargo (IdCargo);
alter table EquipaTecnica add constraint FKIDCARGO Foreign key (Cargo) references Cargo (IdCargo);

alter table Incidencia add constraint FKTIPOINCIDENCIA Foreign key (TipoIncidencia) references TipoIncidencia (Incidencia); 
alter table Incidencia add constraint FKTIPOINCIDENCIA Foreign key (Cartao) references Cartao (idCartao); 
alter table Incidencia add constraint FKTIPOINCIDENCIAAA Foreign key (TipoIncidencia) references TipoIncidencia(idTipoIncidencia);

alter table Jogadores add constraint FKCLUBE Foreign key (Clube) references Clube (IdClube) ; 
alter table Jogadores add constraint FKINCIDENCIA Foreign key (Incidencia) references Incidencia (idIncidencia) ; 
alter table Jogadores add constraint FKNACIONALIDADE Foreign Key (Nacionalidade) references Nacionalidade (idNacionalidade);
alter table Jogadores add constraint FKPOSICAO Foreign Key (Posicao) references Posicao (IDPosicao);

alter table Jogo add Constraint FKTORNEIO Foreign Key (Torneio) references Torneio (IdTorneio);
alter table Jogo add constraint FKINCIDENCIASJOGO Foreign Key (Incidencias) references Incidencia(idIncidencia);
alter table Jogo add constraint FKESTATISTICAJOGO Foreign Key (Estatistica) references Estatisticas (IdEstatistica);

alter table Torneio add Constraint FKCLUBETOR Foreign Key (Clubes) references Clube (IdClube);



COMMIT
