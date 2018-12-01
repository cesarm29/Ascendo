--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

-- Started on 2018-11-30 23:40:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = true;

--
-- TOC entry 186 (class 1259 OID 16387)
-- Name: cajero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cajero (
    idcajero integer NOT NULL,
    denominacion integer NOT NULL,
    cantidad integer NOT NULL
);


ALTER TABLE public.cajero OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16385)
-- Name: cajero_idcajero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cajero_idcajero_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cajero_idcajero_seq OWNER TO postgres;

--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 185
-- Name: cajero_idcajero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cajero_idcajero_seq OWNED BY public.cajero.idcajero;


--
-- TOC entry 188 (class 1259 OID 16395)
-- Name: retiros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.retiros (
    idretiros integer NOT NULL,
    cantidad numeric(10,0) NOT NULL,
    usuarios_idusuarios integer NOT NULL
);


ALTER TABLE public.retiros OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16393)
-- Name: retiros_idretiros_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.retiros_idretiros_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.retiros_idretiros_seq OWNER TO postgres;

--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 187
-- Name: retiros_idretiros_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.retiros_idretiros_seq OWNED BY public.retiros.idretiros;


--
-- TOC entry 190 (class 1259 OID 16405)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol (
    idrol integer NOT NULL,
    nombre character varying(45) NOT NULL,
    descripcion character varying(45) NOT NULL
);


ALTER TABLE public.rol OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16403)
-- Name: rol_idrol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rol_idrol_seq OWNER TO postgres;

--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 189
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rol_idrol_seq OWNED BY public.rol.idrol;


--
-- TOC entry 192 (class 1259 OID 16413)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    idusuarios integer NOT NULL,
    nombres character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password text NOT NULL,
    telefono integer NOT NULL,
    direccion character varying(150) NOT NULL,
    rol_idrol integer NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16411)
-- Name: usuarios_idusuarios_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_idusuarios_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_idusuarios_seq OWNER TO postgres;

--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 191
-- Name: usuarios_idusuarios_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_idusuarios_seq OWNED BY public.usuarios.idusuarios;


--
-- TOC entry 2020 (class 2604 OID 16390)
-- Name: cajero idcajero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cajero ALTER COLUMN idcajero SET DEFAULT nextval('public.cajero_idcajero_seq'::regclass);


--
-- TOC entry 2021 (class 2604 OID 16398)
-- Name: retiros idretiros; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.retiros ALTER COLUMN idretiros SET DEFAULT nextval('public.retiros_idretiros_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 16408)
-- Name: rol idrol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol ALTER COLUMN idrol SET DEFAULT nextval('public.rol_idrol_seq'::regclass);


--
-- TOC entry 2023 (class 2604 OID 16416)
-- Name: usuarios idusuarios; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN idusuarios SET DEFAULT nextval('public.usuarios_idusuarios_seq'::regclass);


--
-- TOC entry 2150 (class 0 OID 16387)
-- Dependencies: 186
-- Data for Name: cajero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cajero (idcajero, denominacion, cantidad) FROM stdin;
1	2000	2
2	1000	4
3	5000	3
4	100000	5
5	50000	4
\.


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 185
-- Name: cajero_idcajero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cajero_idcajero_seq', 5, true);


--
-- TOC entry 2152 (class 0 OID 16395)
-- Dependencies: 188
-- Data for Name: retiros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.retiros (idretiros, cantidad, usuarios_idusuarios) FROM stdin;
\.


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 187
-- Name: retiros_idretiros_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.retiros_idretiros_seq', 1, false);


--
-- TOC entry 2154 (class 0 OID 16405)
-- Dependencies: 190
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rol (idrol, nombre, descripcion) FROM stdin;
1	admin	perfil usuario administrador
2	cliente	perfil usuario cliente
\.


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 189
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rol_idrol_seq', 2, true);


--
-- TOC entry 2156 (class 0 OID 16413)
-- Dependencies: 192
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (idusuarios, nombres, email, password, telefono, direccion, rol_idrol) FROM stdin;
2	cesar mayorga	cesarleandromayorga7@gmail.com	12345	123456	calle 123	1
3	prueba usuario	usuario@hotmail.com	12345	123456	calle 123	2
\.


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 191
-- Name: usuarios_idusuarios_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_idusuarios_seq', 3, true);


--
-- TOC entry 2025 (class 2606 OID 16392)
-- Name: cajero cajero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cajero
    ADD CONSTRAINT cajero_pkey PRIMARY KEY (idcajero);


--
-- TOC entry 2028 (class 2606 OID 16410)
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);


--
-- TOC entry 2030 (class 2606 OID 16421)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (idusuarios, rol_idrol);


--
-- TOC entry 2026 (class 1259 OID 16401)
-- Name: retiros_usuarios_idusuarios_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX retiros_usuarios_idusuarios_idx ON public.retiros USING btree (usuarios_idusuarios);


--
-- TOC entry 2031 (class 1259 OID 16422)
-- Name: usuarios_rol_idrol_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX usuarios_rol_idrol_idx ON public.usuarios USING btree (rol_idrol);


-- Completed on 2018-11-30 23:40:14

--
-- PostgreSQL database dump complete
--

