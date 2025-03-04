PGDMP  &            	        }            VRDB    17.2    17.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16388    VRDB    DATABASE     }   CREATE DATABASE "VRDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE "VRDB";
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16390    clients    TABLE     �   CREATE TABLE public.clients (
    codigo bigint NOT NULL,
    nome character varying(255) NOT NULL,
    limite_compra bigint NOT NULL,
    dia_fechamento_fatura bigint NOT NULL
);
    DROP TABLE public.clients;
       public         heap r       postgres    false    4            �            1259    16389    clients_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.clients_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.clients_codigo_seq;
       public               postgres    false    218    4            �           0    0    clients_codigo_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.clients_codigo_seq OWNED BY public.clients.codigo;
          public               postgres    false    217            �            1259    16412    products    TABLE     �   CREATE TABLE public.products (
    codigo bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    preco double precision NOT NULL
);
    DROP TABLE public.products;
       public         heap r       postgres    false    4            �            1259    16411    products_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.products_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.products_codigo_seq;
       public               postgres    false    4    220            �           0    0    products_codigo_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.products_codigo_seq OWNED BY public.products.codigo;
          public               postgres    false    219            &           2604    16396    clients codigo    DEFAULT     p   ALTER TABLE ONLY public.clients ALTER COLUMN codigo SET DEFAULT nextval('public.clients_codigo_seq'::regclass);
 =   ALTER TABLE public.clients ALTER COLUMN codigo DROP DEFAULT;
       public               postgres    false    217    218    218            '           2604    16418    products codigo    DEFAULT     r   ALTER TABLE ONLY public.products ALTER COLUMN codigo SET DEFAULT nextval('public.products_codigo_seq'::regclass);
 >   ALTER TABLE public.products ALTER COLUMN codigo DROP DEFAULT;
       public               postgres    false    219    220    220            �          0    16390    clients 
   TABLE DATA           U   COPY public.clients (codigo, nome, limite_compra, dia_fechamento_fatura) FROM stdin;
    public               postgres    false    218   [       �          0    16412    products 
   TABLE DATA           <   COPY public.products (codigo, descricao, preco) FROM stdin;
    public               postgres    false    220   `       �           0    0    clients_codigo_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.clients_codigo_seq', 19, true);
          public               postgres    false    217            �           0    0    products_codigo_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.products_codigo_seq', 6, true);
          public               postgres    false    219            )           2606    16398    clients clients_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (codigo);
 >   ALTER TABLE ONLY public.clients DROP CONSTRAINT clients_pkey;
       public                 postgres    false    218            +           2606    16420    products products_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (codigo);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public                 postgres    false    220            �   �   x�M��N� �ׇ��	L�23�:��Y���MKR�ZM|6w���?� �|炆��I^��I�M���0��_!�,-u�O0/(-x�sL�粩�|���@.Ȗ�[I���)��"�6EW�6YU8��+��q>�Y
��e��2j�9���.��#������;��x>��ЍP�ni(����c�"O1�e�ӎ�>�e��Y,��fn8�u�j־㖼��s�X���I;B��`�7�d�R�*�r'���$^�      �   x   x��A
�@�ur��`hmG����F]vᦥ���IEo�x��vt��<娥ȹڒWT�cӄ��PUf����3�P6u�ԦR�\�U��HÄ���YM���{���c>zF�H]��{`�/0�#�     