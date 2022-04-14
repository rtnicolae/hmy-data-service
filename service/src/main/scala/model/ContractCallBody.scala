package model

import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

case class ContractCallBody(inputs: (ContractCallInfo, String)) //bleah

object ContractCallBody {
  implicit val decoder: JsonDecoder[ContractCallBody] = DeriveJsonDecoder.gen[ContractCallBody]
  implicit val encoder: JsonEncoder[ContractCallBody] = DeriveJsonEncoder.gen[ContractCallBody]
}
