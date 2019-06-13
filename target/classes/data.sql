/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  AdamPrzedlacki
 * Created: 2019-02-24
 */


INSERT INTO roles(name)	VALUES ('USER'), ('ADMIN') ON CONFLICT DO NOTHING;

INSERT INTO public.branch(
	name_branch)
	VALUES ('Programista'), ('Tester')
        ON CONFLICT DO NOTHING;

INSERT INTO public.employees(
	email, first_name, last_name, password, username, branch_id, role_id)
	VALUES ('admin@admin.pl', 'admin', 'admin', 'admin', 'admin1', 2, 2)
        ON CONFLICT DO NOTHING;