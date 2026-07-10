package modelo;

/**
 * Contrato que deben cumplir todos los residuos del sistema.
 * Permite tratar cualquier tipo de residuo de forma homogénea (polimorfismo),
 * sin que el resto del sistema conozca las clases concretas.
 */
public interface IResiduo {
    String getID();

    double getPeso();

    String getTipo();
}