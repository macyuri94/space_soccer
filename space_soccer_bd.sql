---------------------------------------------------------------
--Tabela PUCLICO
create table Publico (IdPublico int primary key, 
					ImagemPerfil image  not null,
					Email varchar(50) unique  not null, 
					Passwordd varchar(30) not null, 
					DataNascimento datetime    not null,
					Username varchar(50) unique not null,
					NivelAcesso int not null); 
			
---------------------------------------------------------------
--Tabela ESTADIO			
create table Estadio (IdEstadio int primary key, 
					Nome varchar(30) not null,
					NumLugares int not null, 
					Cidade varchar(30) not null, 
					Historia text );

---------------------------------------------------------------
--Tabela CARGo
create table Cargo (IdCargo int Primary key not null , 
					NomeCargo varchar(30) not null, 
					sigla varchar(30)  not null
					);

---------------------------------------------------------------
--Tabela Equipa Tecnica						
create table EquipaTecnica (CC int Primary key , 
							Idade int not null, 
							Nome varchar(40) not null, 
							Email varchar(40) not null unique, 
							IdCargo int  not null REFERENCES Cargo(IdCargo) ); --chave estrangeira para cargo e quem caralhos meteu id fds pqp 
				
			
---------------------------------------------------------------
--Tabela Clube 
create table Clube (IdClube int  primary key,
					Presidente varchar(40) not null , 
					AnoFundacao datetime  not null, 
					CidadeFundacao varchar(40) not null,
					Logotipo image not null,
					EquipaTecnica int not null REFERENCES EquipaTecnica(CC) , --Chave estrangeira da Equipa tecnica
					Estadio int REFERENCES Estadio(IdEstadio) ,--Chave estrangeira do estadio
					Publico int REFERENCES Publico (IdPublico) );--Chave estrangeira do publico

							
---------------------------------------------------------------
--Tabela CARTAO
create table Cartao (idCartao int primary key, 
					CorCartao varchar(10) not null ,
					NomeCartao varchar (10) not null
					);


							
---------------------------------------------------------------
--Tabela TipoIncidencia
create table TipoIncidencia (idTipoIncidencia int primary key, 
							TipoIncidencia varchar(30) not null ,
							Minuto int not null);
							
---------------------------------------------------------------
--Tabela Incidencia	
create table Incidencia (idIncidencia  int primary key,
						TipoIncidencia int not null REFERENCES TipoIncidencia(idTipoIncidencia) , --chave estrangeira da TipoEIncidencia
						Cartao int REFERENCES Cartao(idCartao) , --chave estrangeira do cartao 
						Jogador int not null);
														
							
---------------------------------------------------------------
--Tabela Nacionalidade 
create table Nacionalidade (IdNacionalidade int primary key ,
							NomeNacionalidade varchar(30) not null,
							Sigla varchar(2) not null );


---------------------------------------------------------------
--Tabela Posicao 
create table Posicao (IDPosicao int primary key ,
						NomePosicao varchar(30) not null,
						SiglaPosicao varchar(2) not null );



---------------------------------------------------------------
--Tabela Jogadores 
create table Jogadores (CodAcesso  int not null  ,
						Nome varchar(40) not null, 
                        NumCamisola int not null,
                        Email varchar(40)  Primary Key not null ,
                        Idade int not null,
                        Nacionalidade int not null REFERENCES Nacionalidade(IdNacionalidade) , --chave estrangeira da Nacionalidades 
                        Posicao int  not null REFERENCES Posicao(IDPosicao), --chave estrangeira da posicao
                        Altura int not null , 
                        Peso int,		
						Clube int REFERENCES Clube(IdClube) , --chave estrangeira do clube 
						NivelAcesso int not null, 
						Incidencias int REFERENCES Incidencia (idIncidencia)  ); --chave estrangeira da incidencia

-----------------------------------------------------------------
--Tabela Localizacao do torneio 

create table LocalizacaoTorneio ( IdLocalizacao int primary key, 
									Pais varchar(50) not null, 
									Cidade varchar(50) not null, 
									CoordenadaX int not null, 
									CoordenadaY int not null, 
									Informacao text not null) ;

-------------------------------------------------------------------
	--Tabela do torneio 
					
create table Torneio (IdTorneio int primary key , 
						Nome varchar(40) not null, 
						dtInicio datetime not null,
						dtFim datetime not null,
						Campeao varchar(30) , 
						NumEquipas int not null,
						Clubes int REFERENCES Clube(IdClube) , --chave estrangeira dos clubes 
						NumGrupos int not null,  
						Categoria varchar(3) not null,  
						TipoCompeticao varchar(30) not null,
						NumParticipantes int not null,
						Localizacao  int not null REFERENCES LocalizacaoTorneio(IdLocalizacao)  --chave estrangeira da localizacao 
						);

									
							
		


-------------------------------------------------------------------		
--Tabela da estatisticas 
create table Estatisticas (IdEstatistica int primary key , 
							Possedebola int not null, 
							RematesABaliza int not null, 
							RematesTotais int not null, 
							NumIncidencias int not null, 
							Golos int not null, 
							Equipa1 varchar(30) not null, 
							Equipa2 varchar(30) not null
							);
							
	-------------------------------------------------------------------

Create table Jogo (IdJogo int primary key ,
					Arbitro varchar(40) not null, 
					segundoArbitro varchar(40), 
					VideoArbitro varchar(40), 
					TempoJogo int , 
					DataJogo datetime not null,
					Torneio int REFERENCES  Torneio (IdTorneio) ,		--chave estrangeira do torneio 
					Incidencias int REFERENCES Incidencia(idIncidencia)  , 	--chave estrangeira das INCIDENCIAS 
					Estatistica int REFERENCES Estatisticas (IdEstatistica)     --chave estrangeira da estatistica 
					);
	-------------------------------------------------------------------
							
	

								

alter table Jogadores add constraint FKCLUBE Foreign key (Clube) references Clube (IdClube) ; 
-- Jogadores  :	Clube 
alter table Clube add Constraint FKEQUIPATECNICA Foreign Key (EquipaTecnica) references EquipaTecnica (CC);
-- Clube  :	EquipaTecnica 
alter table Clube add Constraint FKESTADIO Foreign Key (Estadio) references Estadio (IdEstadio);
-- Clube  :	Estadio 
alter table Torneio add Constraint FKCLUBETOR Foreign Key (Clubes) references Clube (IdClube);
-- Torneio  :	Clube 
alter table Jogo add Constraint FKTORNEIO Foreign Key (Torneio) references Torneio (IdTorneio);
-- Jogo  :	Torneio 
alter table Clube add Constraint FKPUBLICO Foreign Key (Publico) references Publico (IdPublico);
-- Clube  :	Publico 
alter table Jogadores add constraint FKINCIDENCIA Foreign key (Incidencias) references Incidencia (idIncidencia) ;
-- Jogadores  :	Incidencia 
alter table Incidencia add constraint FKTIPOINCIDENCIA Foreign key (TipoIncidencia) references TipoIncidencia (idTipoIncidencia) ;
-- Incidencia  :	TipoIncidencia 
alter table Incidencia add constraint FKTIPOINCIDENCIAAA Foreign key (Cartao) references Cartao (idCartao) ; 
-- Incidencia  :	Cartao 
alter table EquipaTecnica add constraint FKIDCARGO Foreign key (IdCargo) references Cargo (IdCargo) ;
-- EquipaTecnica  :	Cargo 
alter table Jogadores add constraint FKNACIONALIDADE Foreign Key (Nacionalidade) references Nacionalidade (idNacionalidade);
-- Jogadores  :	Nacionalidade 
alter table Jogadores add constraint FKPOSICAO Foreign Key (Posicao) references Posicao (IDPosicao);
-- Jogadores  :	Posicao 
alter table EquipaTecnica add constraint FKEQUIPATECNICAFUNCAO Foreign key (IdCargo) references Cargo (IdCargo);
-- EquipaTecnica  :	Cargo 
alter table Jogo add constraint FKINCIDENCIASJOGOO Foreign Key (Incidencias) references Incidencia(idIncidencia);
-- Jogo  :	Incidencia 
alter table Incidencia add constraint FKTIPOINCIDENCIAAAS Foreign key (TipoIncidencia) references TipoIncidencia(idTipoIncidencia);
-- Incidencia  :	TipoIncidencia 
alter table Jogo add constraint FKESTATISTICAJOGO Foreign Key (Estatistica) references Estatisticas (IdEstatistica);
-- FKESTATISTICAJOGO  :	Estatisticas 
alter table Torneio add constraint FKINFOTORNEIO Foreign Key (Localizacao) references LocalizacaoTorneio (IdLocalizacao);
-- Torneio  :	LocalizacaoTorneio 
commit
























--ALTER TABLE PACIENTES ADD CONSTRAINT FKPACIENTES293059 FOREIGN KEY (COD_POSTAR) REFERENCES COD_POSTAL (CODIGO);
