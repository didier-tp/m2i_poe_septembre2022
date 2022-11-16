function searchComptes(){
	let numClient = document.getElementById("txtNumClient").value;
	let wsUrl = "./bank-api/compte?numClient="+numClient;
	makeAjaxGetRequest(wsUrl , (jsonResponse) => {
		document.getElementById("spanRes").innerHTML=jsonResponse;
		let jsComptes = JSON.parse(jsonResponse);
		let tbodyElt = document.getElementById("tbody");
		tbodyElt.innerHTML="";
		for(let i in jsComptes){
			let compte = jsComptes[i]; 
			let trElt = tbodyElt.insertRow(-1);
			trElt.insertCell(0).innerHTML=compte.numero;
			trElt.insertCell(1).innerHTML=compte.label;
			trElt.insertCell(2).innerHTML=compte.solde;
		}
		
	});
}