--
-- PostgreSQL database dump
--

-- Dumped from database version 15.11
-- Dumped by pg_dump version 15.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.user_sector DROP CONSTRAINT user_sector_user_id_fkey;
ALTER TABLE ONLY public.user_sector DROP CONSTRAINT user_sector_sector_id_fkey;
ALTER TABLE ONLY public.sector DROP CONSTRAINT sector_parent_id_fkey;
DROP INDEX public.idx_user_sector_user;
DROP INDEX public.idx_user_sector_sector;
DROP INDEX public.idx_sector_parent_id;
DROP INDEX public.idx_app_user_name;
DROP INDEX public.flyway_schema_history_s_idx;
ALTER TABLE ONLY public.user_sector DROP CONSTRAINT user_sector_pkey;
ALTER TABLE ONLY public.sector DROP CONSTRAINT sector_pkey;
ALTER TABLE ONLY public.flyway_schema_history DROP CONSTRAINT flyway_schema_history_pk;
ALTER TABLE ONLY public.app_user DROP CONSTRAINT app_user_pkey;
ALTER TABLE ONLY public.app_user DROP CONSTRAINT app_user_edit_token_key;
ALTER TABLE public.sector ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.app_user ALTER COLUMN id DROP DEFAULT;
DROP TABLE public.user_sector;
DROP SEQUENCE public.sector_id_seq;
DROP TABLE public.sector;
DROP TABLE public.flyway_schema_history;
DROP SEQUENCE public.app_user_id_seq;
DROP TABLE public.app_user;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: helmesuser
--

CREATE TABLE public.app_user (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    agree_to_terms boolean NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    edit_token uuid NOT NULL
);


ALTER TABLE public.app_user OWNER TO helmesuser;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: public; Owner: helmesuser
--

CREATE SEQUENCE public.app_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_id_seq OWNER TO helmesuser;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: helmesuser
--

ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: helmesuser
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO helmesuser;

--
-- Name: sector; Type: TABLE; Schema: public; Owner: helmesuser
--

CREATE TABLE public.sector (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    parent_id bigint
);


ALTER TABLE public.sector OWNER TO helmesuser;

--
-- Name: sector_id_seq; Type: SEQUENCE; Schema: public; Owner: helmesuser
--

CREATE SEQUENCE public.sector_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sector_id_seq OWNER TO helmesuser;

--
-- Name: sector_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: helmesuser
--

ALTER SEQUENCE public.sector_id_seq OWNED BY public.sector.id;


--
-- Name: user_sector; Type: TABLE; Schema: public; Owner: helmesuser
--

CREATE TABLE public.user_sector (
    user_id bigint NOT NULL,
    sector_id bigint NOT NULL
);


ALTER TABLE public.user_sector OWNER TO helmesuser;

--
-- Name: app_user id; Type: DEFAULT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);


--
-- Name: sector id; Type: DEFAULT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.sector ALTER COLUMN id SET DEFAULT nextval('public.sector_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: helmesuser
--

COPY public.app_user (id, name, agree_to_terms, created_at, updated_at, edit_token) FROM stdin;
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: helmesuser
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	Create Sector Table	SQL	V1__Create_Sector_Table.sql	-954111302	helmesuser	2025-02-22 00:57:39.330548	17	t
2	2	Create User Table	SQL	V2__Create_User_Table.sql	1735634729	helmesuser	2025-02-22 00:57:39.378683	17	t
3	3	Create User Sector Table	SQL	V3__Create_User_Sector_Table.sql	1528873349	helmesuser	2025-02-22 00:57:39.411767	14	t
4	4	Insert Core Sectors	SQL	V4__Insert_Core_Sectors.sql	1909911200	helmesuser	2025-02-22 00:57:39.44296	19	t
\.


--
-- Data for Name: sector; Type: TABLE DATA; Schema: public; Owner: helmesuser
--

COPY public.sector (id, name, parent_id) FROM stdin;
1	Manufacturing	\N
2	Service	\N
3	Other	\N
4	Construction materials	1
5	Electronics & Optics	1
6	Food & Beverage	1
7	Furniture	1
8	Machinery	1
9	Metalworking	1
10	Plastic & Rubber	1
11	Printing	1
12	Textile & Clothing	1
13	Wood	1
14	Bakery & Confectionery Products	6
15	Beverages	6
16	Fish & Fish Products	6
17	Meat & Meat Products	6
18	Milk & Dairy Products	6
19	Sweets & Snack Food	6
20	Other (Bakery & Confectionery Products)	6
21	Bathroom/Sauna	7
22	Bedroom	7
23	Children’s Room	7
24	Kitchen	7
25	Living Room	7
26	Office	7
27	Project Furniture	7
28	Outdoor	7
29	Other (Furniture)	7
30	Machinery Components	8
31	Machinery Equipment/Tools	8
32	Manufacture of Machinery	8
33	Maritime	8
34	Metal Structures	8
35	Repair & Maintenance Service	8
36	Other (Machinery)	8
37	Aluminium & Steel Workboats	33
38	Boat/Yacht Building	33
39	Ship Repair & Conversion	33
40	Construction of Metal Structures	9
41	Houses & Buildings	9
42	Metal Products	9
43	Metal Works	9
44	CNC-machining	43
45	Forgings, Fasteners	43
46	Gas, Plasma, Laser Cutting	43
47	MIG, TIG, Aluminum Welding	43
48	Packaging	10
49	Plastic Goods	10
50	Plastic Processing Technology	10
51	Blowing	10
52	Moulding	10
53	Plastics Welding & Processing	10
54	Plastic Profiles	10
55	Advertising	11
56	Book/Periodicals Printing	11
57	Labelling & Packaging Printing	11
58	Clothing	12
59	Textile	12
60	Wooden Building Materials	13
61	Wooden Houses	13
62	Other (Wood)	13
63	Creative Industries	3
64	Energy Technology	3
65	Environment	3
66	Business services	2
67	Engineering	2
68	Information Technology & Telecommunications	2
69	Tourism	2
70	Translation services	2
71	Transport & Logistics	2
72	Data processing, Web portals, E-marketing	68
73	Programming, Consultancy	68
74	Software, Hardware	68
75	Telecommunications	68
76	Air	71
77	Rail	71
78	Road	71
79	Water	71
\.


--
-- Data for Name: user_sector; Type: TABLE DATA; Schema: public; Owner: helmesuser
--

COPY public.user_sector (user_id, sector_id) FROM stdin;
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: helmesuser
--

SELECT pg_catalog.setval('public.app_user_id_seq', 1, false);


--
-- Name: sector_id_seq; Type: SEQUENCE SET; Schema: public; Owner: helmesuser
--

SELECT pg_catalog.setval('public.sector_id_seq', 79, true);


--
-- Name: app_user app_user_edit_token_key; Type: CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_edit_token_key UNIQUE (edit_token);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: sector sector_pkey; Type: CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT sector_pkey PRIMARY KEY (id);


--
-- Name: user_sector user_sector_pkey; Type: CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.user_sector
    ADD CONSTRAINT user_sector_pkey PRIMARY KEY (user_id, sector_id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: helmesuser
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: idx_app_user_name; Type: INDEX; Schema: public; Owner: helmesuser
--

CREATE INDEX idx_app_user_name ON public.app_user USING btree (name);


--
-- Name: idx_sector_parent_id; Type: INDEX; Schema: public; Owner: helmesuser
--

CREATE INDEX idx_sector_parent_id ON public.sector USING btree (parent_id);


--
-- Name: idx_user_sector_sector; Type: INDEX; Schema: public; Owner: helmesuser
--

CREATE INDEX idx_user_sector_sector ON public.user_sector USING btree (sector_id);


--
-- Name: idx_user_sector_user; Type: INDEX; Schema: public; Owner: helmesuser
--

CREATE INDEX idx_user_sector_user ON public.user_sector USING btree (user_id);


--
-- Name: sector sector_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT sector_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES public.sector(id);


--
-- Name: user_sector user_sector_sector_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.user_sector
    ADD CONSTRAINT user_sector_sector_id_fkey FOREIGN KEY (sector_id) REFERENCES public.sector(id);


--
-- Name: user_sector user_sector_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: helmesuser
--

ALTER TABLE ONLY public.user_sector
    ADD CONSTRAINT user_sector_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.app_user(id);


--
-- PostgreSQL database dump complete
--

