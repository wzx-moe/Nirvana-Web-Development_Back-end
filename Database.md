# Import Database

## Install Mongodb
* Follow th instruction of https://www.mongodb.com/docs/manual/installation/

## Import Database
* Download `Nirvanadb` folder
* Run `mongorestore --dir={Path-to-Nirvanadb-folder}`

## Create User
* Run `mongosh`
* In mongo shell, run `use nirvana`
* In mongo shell, run `db.createUser({user:'Nirvana',pwd:'NirvanaQUT',roles:[{role:'readWrite',db:'nirvana'}]})`
* Quit mongo shell
* Import complete
