--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2017-09-25 14:25:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 16396)
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE permissions (
    id_permission bigint NOT NULL,
    permission character varying(45) NOT NULL
);


ALTER TABLE permissions OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16394)
-- Name: permissions_id_permission_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permissions_id_permission_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE permissions_id_permission_seq OWNER TO postgres;

--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 185
-- Name: permissions_id_permission_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE permissions_id_permission_seq OWNED BY permissions.id_permission;


--
-- TOC entry 190 (class 1259 OID 16414)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles (
    id_role bigint NOT NULL,
    role character varying(45) NOT NULL
);


ALTER TABLE roles OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16412)
-- Name: roles_id_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roles_id_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roles_id_role_seq OWNER TO postgres;

--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 189
-- Name: roles_id_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE roles_id_role_seq OWNED BY roles.id_role;


--
-- TOC entry 187 (class 1259 OID 16402)
-- Name: roles_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles_permissions (
    roles_id_role bigint NOT NULL,
    permissions_id_permission bigint NOT NULL
);


ALTER TABLE roles_permissions OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16407)
-- Name: roles_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles_users (
    roles_id_role bigint NOT NULL,
    users_id_user bigint NOT NULL
);


ALTER TABLE roles_users OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16422)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id_user bigint NOT NULL,
    email character varying(255) NOT NULL,
    enabled boolean,
    password character varying(150) NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16420)
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_user_seq OWNER TO postgres;

--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 191
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_user_seq OWNED BY users.id_user;


--
-- TOC entry 2021 (class 2604 OID 16399)
-- Name: permissions id_permission; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissions ALTER COLUMN id_permission SET DEFAULT nextval('permissions_id_permission_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 16417)
-- Name: roles id_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles ALTER COLUMN id_role SET DEFAULT nextval('roles_id_role_seq'::regclass);


--
-- TOC entry 2023 (class 2604 OID 16425)
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id_user SET DEFAULT nextval('users_id_user_seq'::regclass);


--
-- TOC entry 2162 (class 0 OID 16396)
-- Dependencies: 186
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permissions (id_permission, permission) FROM stdin;
\.


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 185
-- Name: permissions_id_permission_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permissions_id_permission_seq', 1, false);


--
-- TOC entry 2166 (class 0 OID 16414)
-- Dependencies: 190
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roles (id_role, role) FROM stdin;
1	ROLE_USER
2	ROLE_ADMIN
\.


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 189
-- Name: roles_id_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roles_id_role_seq', 2, true);


--
-- TOC entry 2163 (class 0 OID 16402)
-- Dependencies: 187
-- Data for Name: roles_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roles_permissions (roles_id_role, permissions_id_permission) FROM stdin;
\.


--
-- TOC entry 2164 (class 0 OID 16407)
-- Dependencies: 188
-- Data for Name: roles_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roles_users (roles_id_role, users_id_user) FROM stdin;
1	1
1	3
1	4
1	5
1	6
2	7
2	2
\.


--
-- TOC entry 2168 (class 0 OID 16422)
-- Dependencies: 192
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id_user, email, enabled, password) FROM stdin;
1	pavelseda@email.cz	t	test1
2	pavlikseda@gmail.com	t	test2
3	test1@gmail.com	t	test3
4	test2@gmail.com	t	test4
5	test3@gmail.com	t	test5
6	test6@gmail.com	t	test6
7	test7@gmail.com	t	test7
\.


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 191
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_user_seq', 7, true);


--
-- TOC entry 2025 (class 2606 OID 16401)
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id_permission);


--
-- TOC entry 2029 (class 2606 OID 16406)
-- Name: roles_permissions roles_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT roles_permissions_pkey PRIMARY KEY (roles_id_role, permissions_id_permission);


--
-- TOC entry 2033 (class 2606 OID 16419)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_role);


--
-- TOC entry 2031 (class 2606 OID 16411)
-- Name: roles_users roles_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_users
    ADD CONSTRAINT roles_users_pkey PRIMARY KEY (roles_id_role, users_id_user);


--
-- TOC entry 2037 (class 2606 OID 16433)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2027 (class 2606 OID 16429)
-- Name: permissions uk_7k466p1d0f5nhp8oht7yp1s0x; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissions
    ADD CONSTRAINT uk_7k466p1d0f5nhp8oht7yp1s0x UNIQUE (permission);


--
-- TOC entry 2035 (class 2606 OID 16431)
-- Name: roles uk_g50w4r0ru3g9uf6i6fr4kpro8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT uk_g50w4r0ru3g9uf6i6fr4kpro8 UNIQUE (role);


--
-- TOC entry 2039 (class 2606 OID 16427)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2043 (class 2606 OID 16449)
-- Name: roles_users fk_3i68k5kaeejschvql1wq3vmm0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_users
    ADD CONSTRAINT fk_3i68k5kaeejschvql1wq3vmm0 FOREIGN KEY (roles_id_role) REFERENCES roles(id_role);


--
-- TOC entry 2040 (class 2606 OID 16434)
-- Name: roles_permissions fk_enmggxfnag3l5438r7llk810g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT fk_enmggxfnag3l5438r7llk810g FOREIGN KEY (permissions_id_permission) REFERENCES permissions(id_permission);


--
-- TOC entry 2042 (class 2606 OID 16444)
-- Name: roles_users fk_fl4winbntnecstatubnxc0ej9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_users
    ADD CONSTRAINT fk_fl4winbntnecstatubnxc0ej9 FOREIGN KEY (users_id_user) REFERENCES users(id_user);


--
-- TOC entry 2041 (class 2606 OID 16439)
-- Name: roles_permissions fk_iwa1iyek3a0h442grhevo13do; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT fk_iwa1iyek3a0h442grhevo13do FOREIGN KEY (roles_id_role) REFERENCES roles(id_role);


-- Completed on 2017-09-25 14:25:53

--
-- PostgreSQL database dump complete
--

