$(document).ready(function() {
	 $(function() {
			$("#attendeesName").autocomplete({
				 source: function(request, response) {
					 $.ajax({
						 url: "/attendance_management/getAttendeesName.html",
						 type: "POST",
						 data: { term: request.term },
						 dataType: "json",
						 multiple:true,
						 success: function(data) {
					   	 response($.map(data, function(v){
							 return {
							 label: v.tagName,
							 value: v.tagName
							 };	
						 }
					   	 ));
						}
				 });
			  }
		 });
	 });
	 
	 
	 
	 $(function() {
			$("#attendeesServiceName").autocomplete({
				 source: function(request, response) {
					 $.ajax({
						 url: "/attendance_management/getAttendeesService.html",
						 type: "POST",
						 data: { term: request.term },
						 dataType: "json",
						 multiple:true,
						 success: function(data) {
					   	 response($.map(data, function(v){
							 return {
							 label: v.tagName,
							 value: v.tagName
							 };	
						 }
					   	 ));
						}
				 });
			  }
		 });
	 });
	 
	 $(function() {
			$("#attendeesProfileName").autocomplete({
				 source: function(request, response) {
					 $.ajax({
						 url: "/attendance_management/getAttendeesServiceProfile.html",
						 type: "POST",
						 data: { term: request.term },
						 dataType: "json",
						 multiple:true,
						 success: function(data) {
					   	 response($.map(data, function(v){
							 return {
							 label: v.tagName,
							 value: v.tagName
							 };	
						 }
					   	 ));
						}
				 });
			  }
		 });
	 });
	 
	 $(function() {
			$("#attendeesFirstTimer").autocomplete({
				 source: function(request, response) {
					 $.ajax({
						 url: "/attendance_management/getAttendeesFirstTimer.html",
						 type: "POST",
						 data: { term: request.term },
						 dataType: "json",
						 multiple:true,
						 success: function(data) {
					   	 response($.map(data, function(v){
							 return {
							 label: v.tagName,
							 value: v.tagName
							 };	
						 }
					   	 ));
						}
				 });
			  }
		 });
	 });
	 
	 
});