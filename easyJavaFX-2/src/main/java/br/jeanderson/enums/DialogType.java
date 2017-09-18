package br.jeanderson.enums;
/**
 * Enum responsável por diferenciar os tipos de DialogFX. Obs: a partir da
 * versão 2.2, o enum não faz a implementação de uma Alert(Dialog).
 *
 * @version 2.2
 * @author Jeanderson
 */
public enum DialogType {
    /**
     * Informa que o tipo do DialogFX é do tipo de sucesso(Operação que ocorre
     * com sucesso).
     */
    SUCESS,
    /**
     * Informa que o tipo do DialogFX é do tipo de informação(Apresentação de
     * uma informação).
     */
    INFORMATION,
    /**
     * Informa que o tipo do DialogFX é do tipo de Atenção(Informando que algo
     * requer uma atenção).
     */
    WARNING,
    /**
     * Informa que o tipo do DialoFX é do tipo de Erro(Informa que algum erro
     * ocorreu).
     */
    ERRO;
}
