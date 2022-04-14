package model

import zio.json._

case class HmyCall(
    jsonrpc: String = "2.0",
    id: Int = 1,
    method: String = "hmyv2_call",
    params: (ContractCallInfo, String),
)

object HmyCall {
  implicit val decoder: JsonDecoder[HmyCall] = DeriveJsonDecoder.gen[HmyCall]
  implicit val encoder: JsonEncoder[HmyCall] = DeriveJsonEncoder.gen[HmyCall]

}
