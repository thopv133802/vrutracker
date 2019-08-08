package com.thopham.projects.research.apiservice.controllers

import com.thopham.projects.research.apiservice.core.Response
import com.thopham.projects.research.apiservice.services.CreateOrganizationResult
import com.thopham.projects.research.apiservice.services.ListOrganizationResult
import com.thopham.projects.research.apiservice.services.OrganizationServiceImpl
import org.springframework.web.bind.annotation.*


data class CreateOrganizationRequest(
        val name: String
)

@RestController
@RequestMapping("organizations")
class OrganizationController(private val organizationService: OrganizationServiceImpl){
    @GetMapping("")
    fun listOrganizations(@RequestAttribute("token") token: String): Response<ListOrganizationResult> {
        return try{
            val payload = organizationService.fetchOrganizations(token)
            Response(
                    true,
                    "Lấy dữ liệu organizations thành công.",
                    payload
            )
        }
        catch(exception: Exception){
            Response(
                    false,
                    exception.message ?: "Thất bại trong quá trình lấy dữ liệu organizations.",
                    null
            )
        }
    }
    @PostMapping("")
    fun createOrganization(@RequestBody request: CreateOrganizationRequest, @RequestAttribute("token") token: String): Response<CreateOrganizationResult> {
        return try{
            val payload = organizationService.createOrganization(token, request.name)
            Response(
                    true,
                    "Tạo organization thành công",
                    payload
            )
        }
        catch(exception: Exception){
            Response(
                    false,
                    exception.message ?: "Thất bại trong quá trình tạo organization.",
                    null
            )
        }
    }
}