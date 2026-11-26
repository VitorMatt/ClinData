package br.edu.ifsc.fln.controller;
import br.edu.ifsc.fln.model.Estado;
import br.edu.ifsc.fln.model.UnidadeClinica;
import br.edu.ifsc.fln.util.ArrayListFormatter;

import java.util.List;
import java.util.ArrayList;

public class UnidadesClinicas {

    List<UnidadeClinica> unidadesClinicas = new ArrayList<>();

        public String listarUnidades() {

            List<UnidadeClinica> unidades = new ArrayList<>();

            unidades.add(new UnidadeClinica(
                    1,
                    "Clínica Vida Plena",
                    Estado.SC,
                    "Florianópolis",
                    "Rua Bocaiúva",
                    1820,
                    "Sala 301"
            ));

            unidades.add(new UnidadeClinica(
                    2,
                    "Centro Médico Santa Helena",
                    Estado.SC,
                    "São José",
                    "Avenida Leoberto Leal",
                    450,
                    "Bloco A"
            ));

            unidades.add(new UnidadeClinica(
                    3,
                    "Clínica Bem Estar",
                    Estado.SC,
                    "Palhoça",
                    "Rua Coronel Bernardino Machado",
                    125,
                    "Sala 02"
            ));

            unidades.add(new UnidadeClinica(
                    4,
                    "Instituto Médico Saúde Integral",
                    Estado.SC,
                    "Blumenau",
                    "Rua Sete de Setembro",
                    890,
                    "2º andar"
            ));

            unidades.add(new UnidadeClinica(
                    5,
                    "Clínica Nova Esperança",
                    Estado.PR,
                    "Curitiba",
                    "Rua Dona Francisca",
                    2100,
                    "Sala 105"
            ));

            unidades.add(new UnidadeClinica(
                    6,
                    "Centro Clínico São Lucas",
                    Estado.RS,
                    "Porto Alegre",
                    "Avenida Marcos Konder",
                    650,
                    "Bloco B"
            ));

            this.unidadesClinicas = unidades;

            ArrayListFormatter formatador = new ArrayListFormatter();

            return formatador.formatarArray(unidades);
    }

    public UnidadeClinica getUnidade(int id) {

            listarUnidades();
            for (UnidadeClinica unidade : this.unidadesClinicas) {

                if (unidade.getIdUnidade() == id) {
                    return unidade;
                }
            }

            System.out.println("Erro ao encontrar unidade clínica!");
            return null;
    }
}
