# language: pt


Funcionalidade: Validação de senha

    Esquema do Cenário: Validação de senha com sucesso
        Dado que a senha é <senha>
        Quando o usuário valida a senha
        Entao a resposta deve conter uma lista vazia de validações
        Exemplos:
            | senha       |
            | "Teste@123" |

    Esquema do Cenário: Validação de senha com falha
        Dado que a senha é <senha>
        Quando o usuário valida a senha
        Entao a resposta deve conter uma lista de validações não vazia
        Exemplos:
            | senha      |
            | "invalida" |

    Esquema do Cenário: Validação de senha com senha vazia
        Dado que a senha é <senha>
        Quando o usuário valida a senha
        Entao a resposta deve conter uma lista de validações não vazia
        Exemplos:
            | senha |
            | ""    |