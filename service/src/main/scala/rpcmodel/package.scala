import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

package object rpcmodel {

  case class ContractCallInfo(to: String, data: String)

  case class ContractCallBody(inputs: (ContractCallInfo, String)) //bleah

  case class ContractCallResponse(jsonrpc: String, id: Int, result: String)

  case class HmyCall(
      jsonrpc: String = "2.0",
      id: Int = 1,
      method: String = "hmyv2_call",
      params: (ContractCallInfo, String),
  )

  object ContractCallInfo {
    implicit val decoder: JsonDecoder[ContractCallInfo] = DeriveJsonDecoder.gen[ContractCallInfo]
    implicit val encoder: JsonEncoder[ContractCallInfo] = DeriveJsonEncoder.gen[ContractCallInfo]
  }

  object ContractCallBody {
    implicit val decoder: JsonDecoder[ContractCallBody] = DeriveJsonDecoder.gen[ContractCallBody]
    implicit val encoder: JsonEncoder[ContractCallBody] = DeriveJsonEncoder.gen[ContractCallBody]
  }

  object ContractCallResponse {
    implicit val decoder: JsonDecoder[ContractCallResponse] = DeriveJsonDecoder.gen[ContractCallResponse]
    implicit val encoder: JsonEncoder[ContractCallResponse] = DeriveJsonEncoder.gen[ContractCallResponse]

  }

  object HmyCall {
    implicit val decoder: JsonDecoder[HmyCall] = DeriveJsonDecoder.gen[HmyCall]
    implicit val encoder: JsonEncoder[HmyCall] = DeriveJsonEncoder.gen[HmyCall]
  }

}
