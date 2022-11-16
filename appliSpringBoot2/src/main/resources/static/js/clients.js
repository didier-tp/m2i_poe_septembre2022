sessionStorage.setItem("token",null);

function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return jsonPayload;
    //return JSON.parse(jsonPayload);
};

var traiterReponse = function(response) {
	//response ici au format "json string"
	let zoneResultat = document.getElementById("spanRes");
	let jsClient = JSON.parse(response);
	zoneResultat.innerHTML = jsClient.number + " " + jsClient.firstName + " " + jsClient.lastName; //...
}

function onSearchClient() {
	let zoneNumClient = document.getElementById("txtNumClient");
	let numClient = zoneNumClient.value;
	console.log("numClient=" + numClient);
	var urlWsGet = "./bank-api/client/" + numClient;
	makeAjaxGetRequest(urlWsGet, traiterReponse); //non bloquant (asynchrone)
	//....
}

function doAjout(){
	
	var prenom = document.getElementById("txtPrenom").value;
	var nom = document.getElementById("txtNom").value;
	var adresse = document.getElementById("txtAdresse").value;
	var email = document.getElementById("txtEmail").value;
	
	var url = "./bank-api/client"

    var callback = function(data){
	   console.log("success data=" + data);
       var message ="donnees sauvegardees cote serveur=" + data;
       document.getElementById("spanMessage").innerHTML="<b>"+message+"</b>";
    }

    var errCallback = function(data){
	   console.log("erreur=" + data);
       var message = (JSON.parse(data)).message;
       document.getElementById("spanMessage").innerHTML="<b>"+message+"</b>";
    }

    var jsCustomerObject = { firstName : prenom , lastName : nom , address : adresse , email : email };
    var jsonData = JSON.stringify(jsCustomerObject);

    var token = sessionStorage.getItem("token");
    makeAjaxPostRequest(url,jsonData,callback,errCallback,token) ;
	//makeAjaxPostRequest(url,jsonData,callback,errCallback,tokenGlobal) ;
	
}

function doLogin(){

	var username = document.getElementById("txtUsername").value;
	var password = document.getElementById("txtPassword").value;

	
	var url = "./api-login/public/login"

    var callback = function(data){
	   console.log("success data=" + data);
       var jwtToken = (JSON.parse(data)).token;
       //tokenGlobal=jwtToken;
       sessionStorage.setItem("token",jwtToken);
       var message ="reponse login=" + data + " payload token=" + parseJwt(jwtToken);
       document.getElementById("spanMessageLogin").innerHTML="<b>"+message+"</b>";
    }

    var errCallback = function(data){
	   console.log("erreur=" + data);
       var message = (JSON.parse(data)).message;
       sessionStorage.setItem("token",null);
       document.getElementById("spanMessageLogin").innerHTML="<b>"+message+"</b>";
    }

    var jsLoginRequestObject = {username , password};
    var jsonData = JSON.stringify(jsLoginRequestObject);

	makeAjaxPostRequest(url,jsonData,callback,errCallback) ;
	
}