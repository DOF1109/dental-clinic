// Muestra un mensaje de error para intentar nuevamente
// Necesita el id del div (con display:none) donde se agregará
function showErrorAlert(divId){
    const errorAlert = '<div class="alert alert-danger alert-dismissible" role="alert">' +
    '<div><strong>Error, intente nuevamente</strong></div>' +
    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">' +
    '</button></div>'
    
    document.querySelector(`#${divId}`).innerHTML = errorAlert;
    document.querySelector(`#${divId}`).style.display = "block";
}

// Muestra un mensaje de éxito
// Necesita el id del div (con display:none) donde se agregará
function showSuccesAlert(divId){
    const successAlert = '<div class="alert alert-success alert-dismissible" role="alert">' +
        '<div>Odontologo cargado!</div>' +
        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">' +
        '</button></div>'

    document.querySelector(`#${divId}`).innerHTML = successAlert;
    document.querySelector(`#${divId}`).style.display = "block";
}

// Muestra un mensaje toast de éxito o error
// Necesita el id del div (toast) en el html
// isSucces es true para un menesaje de éxito y false para error
function showToast(message, isSuccess){
    // El color de fondo depende si es exitoso el mensaje a mostrar
    const bgToast = (isSuccess)? 'text-bg-success' : 'text-bg-danger';

    const divToast = document.createElement('div');
    divToast.innerHTML = `
        <div class="toast-container position-fixed bottom-0 start-50 translate-middle-x p-4">
            <div id="messageToast" class="toast ${bgToast}" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header border-0">
                    <strong class="me-auto">${message}</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>`;

    document.body.appendChild(divToast);

    // Creo una instancia del toast y lo muestro
    const messageToast = document.querySelector('#messageToast');
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(messageToast);
    toastBootstrap.show();
}
