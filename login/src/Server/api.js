const { reset } = require('nodemon');
const dboperations = require('./index');
//var Db=require('./index');

dboperations.getLoginDetails().then(result=>{
 console.log(result);
})