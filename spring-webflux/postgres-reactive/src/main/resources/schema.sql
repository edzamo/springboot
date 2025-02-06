-- public.vehicules definition

-- Drop table

-- DROP TABLE public.vehicules;

DROP TABLE IF EXISTS public.vehicules;

CREATE TABLE public.vehicules (
	id serial4 NOT NULL,
	model text NOT NULL,
	cc numeric NOT NULL,
	release int4 NOT NULL,
	CONSTRAINT vehicules_pkey PRIMARY KEY (id)
);

