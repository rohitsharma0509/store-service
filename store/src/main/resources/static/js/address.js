$(document).ready(function() {
	$("#addAddressBtn").click(function() {
		$.get("manageAddress", function(modal) {
			$("#addressModal").html(modal);
			$("#modalAddAddress").modal("show");
		});
	});
});