/**
 * 
 */
import { Toast } from 'bootstrap.esm.min.js'

var toastTrigger = document.getElementById('helpToastBtn')
var toastHelp = document.getElementById('helpToast')

if (toastTrigger) {
  toastTrigger.addEventListener('click', function () {
    var toast = new Toast(toastHelp)
	print("Se ha presionado")

    toast.show()
  })
}