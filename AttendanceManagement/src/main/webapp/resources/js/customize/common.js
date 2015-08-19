function changeReasonChange(){
	if(document.getElementById('remarks_change').value == 'Invited'){
	      document.getElementById('invitedByAttendee').style.display = 'block';
	      document.getElementById('others_invited').style.display = 'block';
	}
	else{
		document.getElementById('invitedByAttendee').style.display = 'none';
	    document.getElementById('others_invited').style.display = 'none';
	}
}


function changeValueFirstTimer(checkbox){
	if(checkbox.checked){
		 document.getElementById('isFirstTimer').value = true;
		 
	}else{
		 document.getElementById('isFirstTimer').value = false;
		 
	}
}

function changeValueMember(checkbox){
	if(checkbox.checked){
		 document.getElementById('isMember').value = true;
		 
	}else{
		 document.getElementById('isMember').value = false;
		 
	}
}


function ddListGroup(value) {
	var newValue = document.getElementById('groupId').value;
	document.getElementById('groupIdPdfFile').value = newValue;
	/*document.getElementById('groupIdCsvFile').value = newValue;*/
	document.getElementById('groupIdView').value = newValue;

}

function ddListMinistry(value) {
	var newValue = document.getElementById('ministryId').value;
	document.getElementById('ministryIdPdfFile').value = newValue;
	/*document.getElementById('ministryIdCsvFile').value = newValue;*/
	document.getElementById('ministryIdView').value = newValue;

}

function ddListGender(value) {
	var newValue = document.getElementById('gender').value;
	document.getElementById('genderPdfFile').value = newValue;
	/*document.getElementById('genderCsvFile').value = newValue;*/
	document.getElementById('genderView').value = newValue;

}
