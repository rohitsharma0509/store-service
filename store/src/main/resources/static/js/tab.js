function loadTab(tab, url, data) {
	var target = tab.data("target"); // the target pane
	$(".spinner-grow").show();

	$(target).load(url, data, function(result) {
		tab.tab('show');
		$(".spinner-grow").hide();

	});
}

function submitForm(e, f, tabId) {
	e.preventDefault();
	var tab = $('.nav-tabs a[id="' + tabId + '"]');
	var url = tab.attr("href");
	var data = $(f).serialize();
	loadTab(tab, url, data);
}

function submitToUrl(e, f, tabId, url) {
	e.preventDefault();
	var tab = $('.nav-tabs a[id="' + tabId + '"]');
	var data = $(f).serialize();
	loadTab(tab, url, data);
}

function getPage(url, tabId) {
	var tab = $('.nav-tabs a[id="' + tabId + '"]');
	loadTab(tab, url, null);
}

function showTabOnStartUp(id) {
	$('#'+id).tab('show');	
}

function openTabAndShowSuccessMsg(tabId, elToRemoveClass, removeClass, targetEl, msg) {
	var tab = $('.nav-tabs a[id="' + tabId + '"]');
	var url = tab.attr("href");
	var data = null;
	var target = tab.data("target"); // the target pane
	$(".spinner-grow").show();

	$(target).load(url, data, function(result) {
		tab.tab('show');
		$(".spinner-grow").hide();
		$("#"+elToRemoveClass).removeClass(removeClass);
	    $("#"+targetEl).text(msg);
	});
}

$(function() {
	$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		var tab = $(this); // this tab
		var url = tab.attr("href"); // the remote url for content

		loadTab(tab, url, null);
	});
});