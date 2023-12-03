// Muestra un mensaje toast de éxito o error
// isSucces es true para un menesaje de éxito y false para error
function showToast(message, isSuccess){
    // El color de fondo depende si es exitoso o no el mensaje a mostrar
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

// Muestra el modal segun el id pasado por parametro
function showModal(idModal, idBtnCancel){
    const htmlModal = document.querySelector(`#${idModal}`);
    const modalBootstrap = bootstrap.Modal.getOrCreateInstance(htmlModal);
    modalBootstrap.show();

    const btnCancelModal = document.querySelector(`#${idBtnCancel}`);
    btnCancelModal.addEventListener('click', () => {
        htmlModal.parentNode.removeChild(htmlModal);

        if (idModal === 'odontologoEditModal') insertEditModal();
        if (idModal === 'odontologoDeleteModal') insertDeleteModal();

        location.reload();
    });
}
