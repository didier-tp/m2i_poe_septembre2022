function searchComptes(){
	let numClient = document.getElementById("txtNumClient").value;
	let wsUrl = "./bank-api/compte?numClient="+numClient;
	makeAjaxGetRequest(wsUrl , (jsonResponse) => {
		let jsComptes = JSON.parse(jsonResponse);
		//....
		document.getElementById("spanRes").innerHTML=jsonResponse;
	});
}