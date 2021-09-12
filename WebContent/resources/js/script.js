var arrayIdsElementsPage = new Array;
var idundefined = 'idundefined';
var classTypeString = 'java.lang.String';
var classTypeLong = 'java.lang.Long';
var classTypeDate = 'java.util.Date';
var classTypeBoolean = 'java.lang.Boolean';
var classTypeBigDecimal = 'java.math.BigDecimal';

function invalidarSession(context, pagina) {
	document.location = (context + pagina + ".jsf");
}

function validarSenhaLogin() {
	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;

	if (j_username === null || j_username.trim() === '') {
		alert("Informe o login!");
		$('#j_username').focus();
		return false;
	}

	if (j_password === null || j_password.trim() === '') {
		alert("Informe a senha!");
		$('#j_password').focus();
		return false;
	}

	return true;
}

function logout(contextPath) {
	var post = 'invalidarSession';

	$.ajax({
		type: 'POST',
		url: post
	}).always(function(resposta) {
		document.location = contextPath + '/j_spring_security_logout';
	});
}

function abrirMenuPop() {
	$('#menupop').show('slow').mouseleave(function() {
		fecharMenuPop();
	});
}

function fecharMenuPop() {
	if ($('#menupop').is(':visible')) {
		$('#menupop').hide('slow');
	}
}

function redirecionarPagina(context, pagina) {
	pagina = pagina + ".jsf";
	document.location = context + pagina;
}

function carregarIdElementosPagina() {
	arrayIdsElementsPage = new Array;
	for (form = 0; form <= document.forms.length; form++) {
		var formAtual = document.forms[form];
		if (formAtual != undefined) {
			for (i = 0; i < document.forms[form].elements.length; i++) {
				if (document.forms[form].elements[i].id != '') {
					arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
				}
			}
		}
	}
}

function getValorElementoPorId(id) {
	carregarIdElementosPagina();
	for (i = 0; i < arrayIdsElementsPage.length; i++) {
		var valor = "" + arrayIdsElementsPage[i];
		if (valor.indexOf(id) > -1) {
			return valor;
		}
	}
	return undefined;
}

function localeData_pt_br() {
	PrimeFaces.locales['pt'] = {
		closeText: 'Fechar',
		prevText: 'Anterior',
		nextText: 'Próximo',
		currentText: 'Começo',
		monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio',
			'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
			'Dezembro'],
		monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
			'Ago', 'Set', 'Out', 'Nov', 'Dez'],
		dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
			'Sexta', 'Sábado'],
		dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
		dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
		weekHeader: 'Semana',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: '',
		timeOnlyTitle: 'São Horas',
		timeText: 'Tempo',
		hourText: 'Hora',
		minuteText: 'Minuto',
		secondText: 'Segundo',
		ampm: false,
		month: 'Mês',
		week: 'Semana',
		day: 'Dia',
		allDayText: 'Todo o Dia'
	};
}

function initTemplate() {
	$(document).ready(function() {
		$('#menupop').hide();
		$('#barramenu').hide();
		$('#barramenu').css("left", "-200px");
		$('#iniciarmenu').click(function() {
			if ($('#barramenu').is(':visible')) {
				ocultarMenu();
			} else {
				$('#barramenu').show();
				$('#barramenu').animate({ "left": "0px" }, "slow");
			}
		});
	});
}

function ocultarMenu() {
	$('#barramenu').animate({ "left": "-200px" }, "slow", function() {
		$('#barramenu').hide();
	});
}

function addFocoAoCampo(campo) {
	var id = getValorElementoPorId(campo);
	if (id != undefined) {
		document.getElementById(id).focus();
	}
}

function gerenciaTeclaEnter() {
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			return (code === 13) ? false : true;

		});

		$('input[type=text]').keydown(function(e) {
			// Obter o próximo índice do elemento de entrada de texto
			var next_idx = $('input[type=text]').index(this) + 1;

			// Obter o número de elemento de entrada de texto em um documento html
			var tot_idx = $('body').find('input[type=text]').length;

			// Entra na tecla no código ASCII
			if (e.keyCode === 13) {
				if (tot_idx === next_idx)
					// Vá para o primeiro elemento de texto
					$('input[type=text]:eq(0)').focus();
				else
					// Vá para o elemento de entrada de texto seguinte
					$('input[type=text]:eq(' + next_idx + ')').focus();
			}
		});
	});
}

function getValorElementPorId(id) {
	carregarIdElementosPagina();
	for (i = 0; i < arrayIdsElementsPage.length; i++) {
		var valor = "" + arrayIdsElementsPage[i];
		if (valor.indexOf(id) > -1) {
			return valor;
		}
	}
	return idundefined;
}

function getValorElementPorIdJQuery(id) {
	var id = getValorElementPorId(id);
	if (id.trim() != idundefined) {
		return PrimeFaces.escapeClientId(id);
	}

	return idundefined;
}

function permitNumber(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function validarCampoPesquisa(valor) {
	if (valor != undefined && valor.value != undefined) {
		if (valor.value.trim() === '') {
			valor.value = '';
		} else {
			valor.value = valor.value.trim();
		}
	}
}

function addMascaraPesquisa(elemento) {
	var id = getValorElementPorIdJQuery('valorPesquisa');
	var vals = elemento.split("*");
	var campoBanco = vals[0];
	var typeCampo = vals[1];

	$(id).unmask();
	$(id).unbind("keypress");
	$(id).unbind("keyup");
	$(id).unbind("focus");
	$(id).val('');
	if (id != idundefined) {
		jQuery(function($) {
			if (typeCampo === classTypeLong) {
				$(id).keypress(permitNumber);
			}
			else if (typeCampo === classTypeBigDecimal) {
				$(id).maskMoney({ precision: 4, decimal: ",", thousands: "." });
			}
			else if (typeCampo === classTypeDate) {
				$(id).mask('99/99/9999');
			}
			else {
				$(id).unmask();
				$(id).unbind("keypress");
				$(id).unbind("keyup");
				$(id).unbind("focus");
				$(id).val('');
			}
			addFocoAoCampo("valorPesquisa");
		});
	}
}

function pesquisaUsuarioDestinoPerderFocoDialog(codUsuario) {
	if (codUsuario != '') {
		$("#usuDestinoMsgDialog").val('');
		$('#loginDestinoMsgDialog').val('');
		$.get("buscarUsuarioDestinoMsg?codEntidade=" + codUsuario, function(resposta) {
			if (resposta.trim() != '') {
				var entidade = JSON.parse(resposta);
				$("#usuDestinoMsgDialog").val(entidade.ent_codigo);
				$('#loginDestinoMsgDialog').val(entidade.ent_login);
			}
		});
	}
}