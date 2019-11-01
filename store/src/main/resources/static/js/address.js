function addAddress() {
	var url = "manageAddress";
	makeCall(url);
}

function editAddress(addressId) {
	var url = "manageAddress?id=" + addressId;
	makeCall(url);
}

function makeCall(url) {
	$.get(url, function(modal) {
		$("#addressModal").html(modal);
		$("#modalAddAddress").modal("show");
	});
}
