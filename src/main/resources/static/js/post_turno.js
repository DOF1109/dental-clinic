window.addEventListener('load', function() {

    cargarSelectPacientes("");
    cargarSelectOdontologos("");

    // Formulario donde el usuario cargará el nuevo turno
    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // Obtengo el paciente seleccionado
        const selectPacientes = document.getElementById('selectPacientes');
        const selectedOptionPacientes = selectPacientes.options[selectPacientes.selectedIndex];

        // Obtengo el odontologo seleccionado
        const selectOdontologos = document.getElementById('selectOdontologos');
        const selectedOptionOdontologos = selectOdontologos.options[selectOdontologos.selectedIndex];

        // JSON con los datos del turno
        const formData = {
            paciente: {
                id: selectedOptionPacientes.value
            },
            odontologo: {
                id: selectedOptionOdontologos.value
            },
            fechaTurno: document.getElementById('fechaTurno').value,
        };

        const url = '/turno';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            showToast('Turno cargado!', true);
            resetUploadForm();
        })
        .catch(error => {
            showToast('Error, intente nuevamente', false);
        })
    });

    function resetUploadForm(){
        document.getElementById('selectPacientes').selectedIndex = 0;
        document.getElementById('selectOdontologos').selectedIndex = 0;
        document.getElementById('fechaTurno').value = "";
    }

});
