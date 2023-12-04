window.addEventListener('load', function() {

    cargarSelectPacientes();
    cargarSelectOdontologos();

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
    
    // Carga los pacientes de la BBDD en las opciones del select
    function cargarSelectPacientes() {
        const urlListarPacientes = '/paciente/todos';
        const settings = {
            method: 'GET'
        }
    
        fetch(urlListarPacientes, settings)
        .then(response => response.json()) // si es GET va en 1 linea
        .then(data => {
            // Recorremos la colección de pacientes del JSON
            for(paciente of data){
                // Por cada paciente armaremos una fila del select
                const selectPacientes = document.getElementById("selectPacientes");
    
                const pacienteOption = document.createElement("option");
                // pacienteOption.value = JSON.stringify(paciente);
                pacienteOption.value = paciente.id;
                pacienteOption.text = paciente.cedula + " - " +
                    paciente.nombre + " " + 
                    paciente.apellido;
    
                selectPacientes.appendChild(pacienteOption);
            };
        })
        .catch(error => {
            console.log(error);
            showToast('Error, actualice la página', false);
        });
    }
    
    // Carga los odontologos de la BBDD en las opciones del select
    function cargarSelectOdontologos() {
        const urlListarOdontologos = '/odontologo/todos';
        const settings = {
            method: 'GET'
        }
    
        fetch(urlListarOdontologos, settings)
        .then(response => response.json()) // si es GET va en 1 linea
        .then(data => {
            // Recorremos la colección de odontologos del JSON
            for(odontologo of data){
                // Por cada odontologo armaremos una fila del select
                const selectOdontologos = document.getElementById("selectOdontologos");
                
                const odontologoOption = document.createElement("option");
                // odontologoOption.value = JSON.stringify(odontologo);
                odontologoOption.value = odontologo.id;
                odontologoOption.text = odontologo.matricula + " - " +
                    odontologo.nombre + " " + 
                    odontologo.apellido;
                
                selectOdontologos.appendChild(odontologoOption);
            };
        })
        .catch(error => {
            console.log(error);
            showToast('Error, actualice la página', false);
        });
    }

});
