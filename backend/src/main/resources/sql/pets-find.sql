select pets.id
      ,pets.name
      ,pets.birth_date
      ,pets.type_id
      ,pets.owner_id
      ,types.name		as	type_name
from pets
left outer join types on pets.type_id =	types.id
/*IF ownerId != 0*/
where pets.owner_id	= /*ownerId*/3
/*END*/
