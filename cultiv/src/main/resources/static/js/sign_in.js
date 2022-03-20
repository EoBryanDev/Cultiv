validaLogin = () => {

    var name = document.getElementById('nameLogin').value;
    var email = document.getElementById('emailLogin').value;
    var cpf = document.getElementById('cpfLogin').value;
    var senha = document.getElementById('senhaLogin').value;
    var telefone = document.getElementById('telefoneLogin').value;
    var celular = document.getElementById('celLogin').value;
    var cep = document.getElementById('cepLogin').value;
    var numero = document.getElementById('numeroCasaLogin').value;
    var endereco = document.getElementById('logradouroLogin').value;
    var bairro = document.getElementById('bairroLogin').value;
    var cidade = document.getElementById('cidadeLogin').value;
    var estado = document.getElementById('estadoLogin').value;
    var complemento = document.getElementById('complementoLogin').value;

    if (name == "" || name == undefined) {

        alert('Preencha o campo Nome')
        document.getElementById('nameLogin').focus()

        return false
    }
    if (cpf == "" || cpf == undefined) {

        alert('Preencha o campo CPF')
        document.getElementById('cpfLogin').focus()

        return false
    }
    if (email == "" || email == undefined) {

        alert('Preencha o campo Email')
        document.getElementById('emailLogin').focus()

        return false
    }
    if (senha == "" || senha == undefined) {

        alert('Preencha o campo Senha')
        document.getElementById('senhaLogin').focus()
              

        return false
    }
    if(senha.length < 6){
			alert('Preencha o campo senha com mais de 6 caracteres!')
	        document.getElementById('senhaLogin').focus()
	        
	        return false
		}
	if (tel == "" || tel == undefined) {

    alert('Preencha o campo Telefone')
    document.getElementById('telefoneLogin').focus()

    return false
	}
	
    if (celular == "" || celular == undefined) {

        alert('Preencha o campo Celular')
        document.getElementById('celLogin').focus()

        return false
    }
    if (cep == "" || cep == undefined) {

        alert('Preencha o campo CEP')
        document.getElementById('cepLogin').focus()

        return false
    }
    if (numero == "" || numero == undefined) {

        alert('Preencha o campo Número')
        document.getElementById('numeroCasaLogin').focus()

        return false
    }


    var form = document.querySelector("form")

    form.addEventListener("submit" , (evento) =>{
        evento.preventDefault()
    })

}

maskCPF = (press) => {
    let num = press.value

    if (num.length == 3) {
        press.value = num += "."
    } if (num.length == 7) {
        press.value = num += "."
    } if (num.length == 11) {
        press.value = num += "-"
    } else {
        press.value = num
    }
}

SomenteNumero = (e) => {
    var tecla = (window.event) ? event.keyCode : e.which

    if (tecla > 47 && tecla < 58 || tecla >= 96 && tecla <= 105)
        return true
    else {
        if (tecla == 8 || tecla == 0 || tecla == 9)
            return true

        else
            return false;
    }
}

//13327-520
maskCEP = (press) => {
    let num = press.value

    if (num.length == 5) {
        press.value = num += "-"
    } else {
        press.value = num
    }
}
//(11) 9 9999-9999
maskCEL = (press) => {
    let num = press.value
    if (num.length == 0) {
        press.value = num += "("
    }
    if (num.length == 3) {
        press.value = num += ")"
    } if (num.length == 4) {
        press.value = num += " "
    } if (num.length == 6) {
        press.value = num += " "
    } if (num.length == 11) {
        press.value = num += "-"
    } else {
        press.value = num
    }
}

maskTEL = (press) => {
    let num = press.value
    if (num.length == 0) {
        press.value = num += "("
    }
    if (num.length == 3) {
        press.value = num += ")"
    } if (num.length == 4) {
        press.value = num += " "
    } if (num.length == 9) {
        press.value = num += "-"
    } else {
        press.value = num
    }
}
//pesquisa de CEP na API

var pesquisarCep = async () => {

    let cep = document.getElementById('cepLogin').value;
    let cepFormatado = cep.replace('-', '')
    let url = `http://viacep.com.br/ws/${cepFormatado}/json`;
    let dados = await fetch(url);
    let end = await dados.json();
    carrega_form(end);
}

//Carrega os valores retornados da API no formulário
var carrega_form = (end) => {

    if (end.erro) {
        alert('CEP não existe');
        document.getElementById('logradouroLogin').value = "";
        document.getElementById('bairroLogin').value = "";
        document.getElementById('cidadeLogin').value = "";
        document.getElementById('estadoLogin').value = "";
    } else {
        document.getElementById('logradouroLogin').value = end.logradouro;
        document.getElementById('bairroLogin').value = end.bairro;
        document.getElementById('cidadeLogin').value = end.localidade;
        document.getElementById('estadoLogin').value = end.uf;
        document.getElementById('numeroCasaLogin').focus();
    }
}
