package br.jeanderson.enums;

import br.jeanderson.interfaces.ValidateMessage;

public enum ValidateType implements ValidateMessage {
	NONE {

		public String message(String fieldName) {
			return "Campo " + fieldName + " não estar preenchido corretamente! Por favor verifique.";
		}

	},
	CPF {

		public String message(String fieldName) {

			return "Campo " + fieldName + " não está preenchido corretamente! Para este tipo de campo deve estar no"
					+ " seguinte formato: xxx.xxx.xxx-xx";
		}

	},
	TELEFONE {

		public String message(String fieldName) {
			return "Campo " + fieldName + " não está preenchido corretamente! Para este tipo de campo deve estar no"
					+ " seguintes formatos: 8 digitos - (xx) xxxx-xxxx ou 9 digitos - (xx) x xxxx-xxxx";
		}

	},
	DATA {

		public String message(String fieldName) {
			return "Campo " + fieldName + " não está preenchido corretamente! Para este tipo de campo deve estar no"
					+ " seguintes formatos: xx/xx/xxxx ou xx-xx-xxxx";
		}

	};
}
