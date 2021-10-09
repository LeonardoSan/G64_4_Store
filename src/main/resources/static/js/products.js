/**
 * 
 */

var missingFileAlertPlaceholder = document.getElementById('missingFileAlertPlaceholder');

function alert(message, type) {
	var wrapper = document.createElement('div');
	wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible alert-sm" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
	
	missingFileAlertPlaceholder.append(wrapper);
}