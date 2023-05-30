/*const express = require('express');
const app = express();
const mssql = require('mssql');
const configure = {
    server : "localhost",
    database : "Login_db",
    user : "PF3ZT1N4\XCT",
    options : {
        encrypt :true,
    },
};
mssql.connect
app.get('/',(req,res)=>{
    res.send("hello World"); 
})
app.listen(3001,() => {
    console.log("running on port 3001");
})
//const { strictEqual } = require('assert');
const mssql = require('mssql/msnodesqlv8');
var config = {
    server : 'PF3ZT1N4\\SQLEXPRESS',
    database : 'Logindb',
    driver : 'msnodesqlv8',
    options : {
        encrypt:true
    }
};

mssql.connect(config,function(err){
    if(err){
        console.log(err);
    }
    var request = new mssql.Request();
    request.query('SELECT * FROM Login_tab',function(err,recordSet){
        if(err){
            console.log(err);
        }else{
            console.log(recordSet);
        }
    });
});
const config = require('./dbconfig');
 const sql=require('msnodesqlv8');
const query = "Select * from Login_tab";
 async function getLoginDetails(){
    try {
     sql.query(config,query,(err,rows)=>{
      console.log(rows);
     })
    } catch (error ) {
     console.log(error);
    }
   }
   module.exports={
    getLoginDetails:getLoginDetails
   }*/

var express = require('express');
var app = express();

app.get('/', function (req, res) {
    
    var sql = require("mssql");

    // config for your database
    var config = {
        server: 'PF3ZT1N4\\SQLEXPRESS', 
        database: 'Logindb',
        driver: 'msnodesqlv8',
        options :{
            trustedConnection: true,
            encrypt: true,
            enableArithAbort: true,
            trustServerCertificate: true
        }
    };

    // connect to your database
    sql.connect(config, function (err) {
    
        if (err) console.log(err);
        // create Request object
        var request = new sql.Request();
            
        // query to the database and get the records
        request.query('select * from Login_tab', function (err) {
            
            if (err) console.log(err)

            // send records as a response
        });
    });
});

var server = app.listen(3001, function () {
    console.log('Server is running..');
});