# GreenDonation
##### Trablaho final de Programação Orientada a Objetos</br>Professora: Livia Almada
<hr>

###### O que é:
O sistema Green Donation é uma aplicação mobile destinada a doação de materiais recicláveis. Ele possui dois níveis de uso: Doador, aquele que pode fazer doações pessoais do material reciclável contido em seu domicílio (pessoa física) ou doações via própria empresa (pessoa jurídica); Coletor, aquele que recebe uma doação compatível com alguma solicitação de material feita por ele, dentro do próprio sistema. Cada solicitação de material feita pelo Coletor possui um material específico, que deseja receber, e a região para o qual ela será direcionada.
O sistema fará a distribuição das doações com base nas solicitações, por região, a cada 12 horas. Na distribuição, as primeiras solicitações são atendidas pelas primeiras doações que correspondam com região e material solicitado/doado.
Após a distribuição, as doações atendidas recebem o estado de “em andamento”, que representa o período em que o coletor, contemplado pela doação, vá buscar a doação ou a rejeite pelo sistema. A solicitação atendida pela doação é excluída do sistema, pois já foi correspondida. Caso o coletor busque o material, ele confirma no sistema o recebimento da doação e a mesma passa para o estado “realizada”, caso o coletor rejeite o material, a doação volta para o estado “em espera”, aguardado a próxima distribuição para ser redirecionada à outra solicitação.
Diante do sistema proposto, é esperado considerável redução do acúmulo de lixo que poderia ser reaproveitado nas regiões cuja coleta seletiva não é presente, e além de ajudar o meio ambiente, é possível dar melhores oportunidades aos que trabalham diretamente com o lixo para que não seja necessário exposição à doenças e ambientes inadequados.
###### OBS: Texto retiradado do Documento de Requisitos construido para essa aplicação na disciplina de Introdução a Processos e Requisitos de Software. 

###### Diagrama de Classes:
<img src="https://raw.githubusercontent.com/wiltonribeiro/GreenDonation/master/Diagrama%20de%20Classes/POO-%20DiagramaImage.jpg" />

###### Observações Gerais. Não foi desenvolvido para o software nenhuma implementação persistência de dados, apenas o uso em memória. Dado objetivo da disciplina de POO, o desenvolvimento da aplicação pôs em foco as características da disciplina, bem como o uso da mesma para mostrar a importância do uso dos princípios no desenvolvimento Mobile.
