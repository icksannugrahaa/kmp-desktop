package com.aha.id.pos.ui.main

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.aha.id.pos.component.Button.customButton
import com.aha.id.pos.component.TextField.customTextField
import com.aha.id.pos.data.enums.ButtonColors
import com.aha.id.pos.data.enums.ButtonSize
import com.aha.id.pos.data.model.ButtonColor
import com.aha.id.pos.data.model.MenusTop
import com.aha.id.pos.data.model.ProductItem
import com.aha.id.pos.data.preference.AuthPreference
import com.aha.id.pos.data.remote.network.ApiService
import com.aha.id.pos.data.remote.request.RequestAuthLogin
import com.aha.id.pos.data.remote.request.RequestProductList
import com.aha.id.pos.data.remote.response.ResponseAuthLogin
import com.aha.id.pos.ui.auth.LoginScreen
import com.aha.id.pos.ui.theme.Colors.black100
import com.aha.id.pos.ui.theme.Colors.black200
import com.aha.id.pos.ui.theme.Colors.grey
import com.aha.id.pos.ui.theme.Colors.grey100
import com.aha.id.pos.ui.theme.Colors.grey200
import com.aha.id.pos.ui.theme.Colors.transparent
import com.aha.id.pos.ui.theme.Colors.white
import com.aha.id.pos.ui.theme.Colors.white100
import com.aha.id.pos.ui.theme.Colors.white200
import com.aha.id.pos.ui.theme.Colors.white300
import com.aha.id.pos.ui.theme.Colors.white400
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.vectorResource
import posahaid.composeapp.generated.resources.*
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.ic_user
import posahaid.composeapp.generated.resources.roboto_bold
import posahaid.composeapp.generated.resources.roboto_medium
import kotlin.random.Random

class DashboardScreen(val token: String) : Screen {
    private lateinit var authPreferences: AuthPreference

    @Composable
    override fun Content() {
        authPreferences = AuthPreference("auth_prefs.properties")
        val navigator = LocalNavigator.currentOrThrow
        var search by remember { mutableStateOf("") }
        var scope = rememberCoroutineScope()
        val sampleMenu = listOf(
            MenusTop("Item 1", true),
            MenusTop("Item 2", false),
            MenusTop("Item 3", false),
            MenusTop("Item 4", false)
        )

//        scope.launch {
//            getListProduct(RequestProductList(
//                1, 10, authPreferences.getString("token")
//            ))
//        }

        Column(
            modifier = Modifier.background(white).fillMaxSize().padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource("image/logo.png"), // Replace with your actual logo resource
                        contentDescription = "Aha! Logo",
                        modifier = Modifier.size(75.dp).wrapContentWidth()
                    )
                    Column(
                        Modifier.weight(1f).padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        customTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            placeholder = "Cari (ctrl + k)",
                            modifier = Modifier.width(678.dp),
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(19.dp),
                                    imageVector = vectorResource(Res.drawable.ic_search),
                                    contentDescription = null
                                )
                            },
                            errorMessage = ""
                        )
                    }
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleCard {
                            Text(
                                text = "DC", fontSize = 14.sp, fontFamily = FontFamily(
                                    Font(
                                        Res.font.roboto_bold,
                                        weight = FontWeight.W600
                                    )
                                ), color = Color.White
                            )
                        }
                        Column(modifier = Modifier.wrapContentWidth().padding(horizontal = 8.dp)) {
                            Text(
                                text = authPreferences.getString("name").toString(),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        Res.font.roboto_medium,
                                        weight = FontWeight.W500
                                    )
                                ),
                                color = black200
                            )
                            Text(
                                text = authPreferences.getString("role").toString(),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        Res.font.roboto_regular,
                                        weight = FontWeight.W400
                                    )
                                ),
                                color = black200
                            )
                        }
                        Image(
                            imageVector = vectorResource(Res.drawable.ic_arrow_bottom),
                            contentDescription = "Aha! Logo",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                BasicHorizontalDivider(grey, maxWidth = true)
                HorizontalList(sampleMenu, 1, modifier = Modifier.padding(horizontal = 32.dp))
            }
            Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 32.dp)) {
                HorizontalList(sampleMenu, 2, modifier = Modifier.padding(top = 16.dp))
                Column(modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    Text(
                        "Transaksi",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold)
                        ),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W700,
                        color = black200,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
                Row(Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    customTextField(
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        placeholder = "Search",
                        modifier = Modifier.width(320.dp).padding(end = 12.dp),
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(19.dp),
                                imageVector = vectorResource(Res.drawable.ic_search),
                                contentDescription = null
                            )
                        },
                        errorMessage = ""
                    )
                    customTextField(
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        placeholder = "Filter",
                        modifier = Modifier.width(110.dp),
                        withTrailingIcon = true,
                        trailingIcon = {
                            Image(
                                imageVector = vectorResource(Res.drawable.ic_arrow_bottom),
                                contentDescription = ""
                            )
                        },
                        errorMessage = ""
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    customButton(
                        maxWidth = false,
                        size = ButtonSize.XL,
                        color = ButtonColors.BLACK,
                        text = "Download Excel",
                        icon = vectorResource(Res.drawable.ic_download),
                        modifier = Modifier,
                        modifierChild = Modifier.padding(horizontal = 16.dp),
                        onClick = {
                            authPreferences.clear()
                            navigator.push(LoginScreen())
                        }
                    )
                }
                CustomTable()
            }
        }
    }

    @Composable
    fun CircleCard(content: @Composable () -> Unit) {
        Card(
            modifier = Modifier.width(34.dp).height(34.dp), // Size of the circle,,
            shape = CircleShape,  // Apply circle shape
            elevation = CardDefaults.cardElevation(0.dp),  // Optional: Add elevation for shadow effect
            colors = CardDefaults.cardColors(
                containerColor = grey100  // Set the background color
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                content()  // Add any content inside the card
            }
        }
    }

    @Composable
    fun HorizontalList(data: List<MenusTop>, section: Int, modifier: Modifier) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
        ) {
            itemsIndexed(data) { index, item ->
                when (section) {
                    1 -> ItemMenu(item)
                    2 -> ItemBreadcumb(item, (index == (data.size - 1)))
                }
            }
        }
    }

    @Composable
    fun ItemMenu(item: MenusTop, onClick: () -> Unit? = {}) {
        Column(
            modifier = Modifier
                .padding(end = 56.dp)
                .clickable(
                    onClick = { onClick },
                    indication = rememberRipple(bounded = true, color = black100),
                    interactionSource = MutableInteractionSource()
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(Res.font.roboto_medium)
                ),
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            if (item.isActive)
                BasicHorizontalDivider(black200, customWidth = 41.dp)
        }
    }

    @Composable
    fun ItemBreadcumb(item: MenusTop, isLast: Boolean = false, onClick: () -> Unit? = {}) {
        Row(
            modifier = Modifier
                .clickable(
                    onClick = { onClick },
                    indication = rememberRipple(bounded = true, color = black100),
                    interactionSource = MutableInteractionSource()

                ).fillMaxWidth().wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    Font(if (isLast) Res.font.roboto_medium else Res.font.roboto_regular)
                ),
                fontWeight = if (isLast) FontWeight.W500 else FontWeight.W400,
                textAlign = TextAlign.Start,
                modifier = Modifier.wrapContentSize().align(Alignment.CenterVertically)
            )
            if (!isLast)
                Image(
                    imageVector = vectorResource(Res.drawable.ic_arrow_breadcumb), // Replace with your actual logo resource
                    contentDescription = "Aha! Logo",
                    modifier = Modifier.padding(horizontal = 4.dp).align(Alignment.CenterVertically)
                )
        }
    }

    @Composable
    fun BasicHorizontalDivider(color: Color, maxWidth: Boolean = false, customWidth: Dp = 0.dp) {
        Divider(
            modifier = Modifier
                .let { if (maxWidth) it.fillMaxWidth() else it.width(customWidth) },
            color = color, // Set the color of the divider
            thickness = 1.dp  // Set the thickness of the divider
        )
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ItemsPerPageSelector(selectedItemsPerPage: Int, onItemsPerPageChange: (Int) -> Unit) {
        val itemsPerPageOptions = listOf(10, 20, 50, 100)
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 10.dp)
                    .border(
                        width = 1.dp, // Border width
                        color = white400, // Border color
                        shape = RoundedCornerShape(8.dp) // Rounded corners
                    ), // Padding inside the border
                elevation = 0.dp, // Elevation for shadow
                shape = RoundedCornerShape(8.dp), // Shape for the card
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth().clickable { expanded = true }
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        selectedItemsPerPage.toString(),
                        color = black100,
                        fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(vectorResource(Res.drawable.ic_arrow_bottom), contentDescription = "Dropdown")
                }
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                itemsPerPageOptions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onItemsPerPageChange(option)
                        expanded = false
                    }) {
                        Text(text = option.toString())
                    }
                }
            }
        }
    }

    @Composable
    fun ItemRow(item: ProductItem) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(IntrinsicSize.Min), // Adjust based on content height
            verticalAlignment = Alignment.CenterVertically
        ) {
            // No Column for the Index and Image
            Text(
                text = "${item.id}",
                modifier = Modifier.padding(8.dp)
            )
            Image(
                painter = painterResource("image/product_example.png"),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(text = item.name, fontWeight = FontWeight.Bold)
                Text(text = item.sku, style = MaterialTheme.typography.body2)
            }
            Text(
                text = item.principal,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            Text(
                text = item.treeUnit,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            Text(
                text = "${item.cnv}",
                modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun ItemList(items: List<ProductItem>, onLoadMore: () -> Unit) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(items.size) { index ->
//                if (index >= items.size - 1) {
//                    onLoadMore() // Trigger loading more items when reaching the end
//                }
                ItemRow(item = items[index])
            }
            // Optional: Loading Indicator
//            item {
//                CircularProgressIndicator(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .wrapContentWidth(Alignment.CenterHorizontally)
//                )
//            }
        }
    }

    @Composable
    fun CustomTable() {
        var itemsPerPage by remember { mutableStateOf(10) }
        var currentPage by remember { mutableStateOf(1) }
        val totalItems = 5000 // As an example
        var totalPages by remember { mutableStateOf(totalItems / itemsPerPage) }
        val items by derivedStateOf {
            generateProductList(itemsPerPage, currentPage)
        }

        Scaffold(
            topBar = {
                Pagination(
                    currentPage = currentPage,
                    totalPages = totalPages,
                    itemPerPage = itemsPerPage,
                    totalItems = totalItems,
                    onPageChange = { selectedPage ->
                        currentPage = selectedPage
                        // Handle data loading or updates here when page changes
                    },
                    onFilterSelected = { selectedItemsPerPage ->
                        currentPage = 1
                        totalPages = totalItems / selectedItemsPerPage
                        itemsPerPage = selectedItemsPerPage
                    }
                )

            },
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(Modifier.weight(1f)) {
                        ProductTable(items)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Pagination(
                            currentPage = currentPage,
                            totalPages = totalPages,
                            itemPerPage = itemsPerPage,
                            totalItems = totalItems,
                            onPageChange = { selectedPage ->
                                currentPage = selectedPage
                                // Handle data loading or updates here when page changes
                            },
                            onFilterSelected = { selectedItemsPerPage ->
                                currentPage = 1
                                totalPages = totalItems / selectedItemsPerPage
                                itemsPerPage = selectedItemsPerPage
                            }
                        )
                    }
                }
            },
            bottomBar = {
            }
        )
    }

    @Composable
    fun PaginationButton(
        text: String,
        icon: ImageVector? = null,
        isSelected: Boolean = false,
        onClick: () -> Unit
    ) {
        customButton(
            size = ButtonSize.S,
            color = ButtonColors.WHITE,
            text = text,
            textColor = if (isSelected) black200 else black100,
            maxWidth = false,
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 5.dp).width(45.dp),
            icon = icon,
            isOutline = true,
            iconPositionStart = true,
            modifierChild = Modifier.padding(8.dp),
            onSelectedColor = if (isSelected) ButtonColor(
                border = grey,
                normal = white,
                disabled = grey,
                hover = white200
            ) else null
        )
    }

    @Composable
    fun Pagination(
        currentPage: Int,
        totalPages: Int,
        itemPerPage: Int,
        totalItems: Int,
        onPageChange: (Int) -> Unit,
        onFilterSelected: (Int) -> Unit
    ) {
        val pagesToShow = 3 // Number of page buttons to display

        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Jumlah data per halaman",
                    color = black100,
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                ItemsPerPageSelector(selectedItemsPerPage = itemPerPage) { selectedItemsPerPage ->
                    onFilterSelected(selectedItemsPerPage)
                }
                Text(
                    text = "dari $totalItems data",
                    color = black100,
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 1) {
                    // First Page Button
                    PaginationButton(
                        text = "",
                        icon = vectorResource(Res.drawable.ic_first_pagination),
                        onClick = { onPageChange(1) })

                    // Previous Page Button
                    PaginationButton(
                        text = "",
                        icon = vectorResource(Res.drawable.ic_previous_pagination),
                        onClick = {
                            if (currentPage > 1) onPageChange(currentPage - 1)
                        })
                }

                // Show first page and ellipsis if needed
                if (currentPage > 3) {
                    PaginationButton(text = "1", onClick = { onPageChange(1) })
                    Text("…", modifier = Modifier.padding(horizontal = 8.dp), textAlign = TextAlign.Center)
                }

                // Show pages around the current page
                val startPage = maxOf(1, currentPage - pagesToShow / 2)
                val endPage = minOf(totalPages, currentPage + pagesToShow / 2)

                for (page in startPage..endPage) {
                    PaginationButton(
                        text = page.toString(),
                        isSelected = (page == currentPage),
                        onClick = { onPageChange(page) }
                    )
                }

                // Show ellipsis and last page if needed
                if (currentPage < totalPages - 2) {
                    Text("…", modifier = Modifier.padding(horizontal = 8.dp), textAlign = TextAlign.Center)
                    PaginationButton(text = totalPages.toString(), onClick = { onPageChange(totalPages) })
                }

                if (currentPage < totalPages) {
                    // Next Page Button
                    PaginationButton(
                        text = "",
                        icon = vectorResource(Res.drawable.ic_next_pagination),
                        onClick = {
                            if (currentPage < totalPages) onPageChange(currentPage + 1)
                        })

                    // Last Page Button
                    PaginationButton(
                        text = "",
                        icon = vectorResource(Res.drawable.ic_last_pagination),
                        onClick = { onPageChange(totalPages) })
                }
            }
        }
    }

    @Composable
    fun ProductTable(items: List<ProductItem>) {
        LazyColumn {
            item {
                TableHeader()
            }
            items(items) { item ->
                TableRow(item)
                Divider()
            }
        }
    }

    @Composable
    fun TableHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(white100),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier.padding(16.dp)
            ) {
                Text(
                    text = "No",
                    modifier = Modifier.weight(0.2f),
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = "Informasi Barang",
                    modifier = Modifier.weight(2f),
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = "Principal",
                    modifier = Modifier.weight(1f),
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = "Tree Unit",
                    modifier = Modifier.weight(1f),
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = "CNV",
                    modifier = Modifier.weight(0.5f),
                    fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                    fontWeight = FontWeight.W400
                )
            }
        }
        Divider()
    }

    @Composable
    fun TableRow(item: ProductItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.id.toString(),
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(Res.font.roboto_italic)),
                fontSize = 14.sp,
                color = black100,
                modifier = Modifier.weight(0.2f)
            )
            Row(
                modifier = Modifier.weight(2f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource("image/product_example.png"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.W400,
                        fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                        fontSize = 14.sp,
                        color = black100
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = vectorResource(Res.drawable.ic_sku_external),
                            contentDescription = "SKU",
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 2.dp)
                        )
                        Text(
                            text = item.sku,
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                            fontSize = 14.sp,
                            color = grey200,
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)
                        )
                        Image(
                            imageVector = vectorResource(Res.drawable.ic_sku_internaal),
                            contentDescription = "SKU",
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 2.dp)
                        )
                        Text(
                            text = item.sku,
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                            fontSize = 14.sp,
                            color = grey200,
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)
                        )

                        Image(
                            imageVector = vectorResource(Res.drawable.ic_sku_barcode),
                            contentDescription = "SKU",
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 2.dp)
                        )
                        Text(
                            text = item.sku,
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily(Font(Res.font.roboto_regular)),
                            fontSize = 14.sp,
                            color = grey200,
                            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)
                        )
                    }
                }
            }
            Text(
                text = item.principal,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = item.treeUnit,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = item.cnv.toString(),
                modifier = Modifier.weight(0.5f)
            )
        }
    }

    private val productNames = listOf(
        "Keripik Kentang", "Teh Botol", "Potato Chips", "Kopi Instant",
        "Mie Goreng", "Biskuit Coklat", "Wafer Vanilla", "Minuman Soda",
        "Permen Mint", "Kerupuk Udang", "Susu Kental Manis", "Saus Sambal",
        "Kecap Manis", "Margarin", "Selai Strawberry", "Yogurt"
    )

    private val brands = listOf("Mayora", "Indofood", "Unilever", "Wings", "Garuda Food", "Orang Tua", "Nestle", "ABC")

    private val packagingUnits = listOf("Dus", "Pak", "Pcs", "Box", "Karton", "Sachet")

    fun generateRandomProduct(id: Int): ProductItem {
        val name = "${brands.random()} ${productNames.random()} ${Random.nextInt(10, 500)}gr"
        val principal = brands.random()
        val treeUnit = generateTreeUnit()
        val cnv = String.format("%.2f", Random.nextDouble(0.5, 5.0)).toDouble()

        return ProductItem(
            id = id,
            name = name,
            principal = principal,
            treeUnit = treeUnit,
            cnv = cnv.toString(),
            image = "",
            sku = "-"
        )
    }

    private fun generateTreeUnit(): String {
        val quantity = Random.nextInt(1, 5)
        val unit1 = packagingUnits.random()
        val unit2 = packagingUnits.random()
        val amount = Random.nextInt(2, 10)

        return "$quantity $unit1 $amount $unit2"
    }

    fun generateProductListItem(count: Int, startIndex: Int): List<ProductItem> {
        return (startIndex until startIndex + count).map { generateRandomProduct(it) }
    }

    fun generateProductList(count: Int, page: Int): List<ProductItem> {
        val startIndex = (page - 1) * count + 1
        return generateProductListItem(count, startIndex)
    }

    private suspend fun getListProduct(requestProductList: RequestProductList) {
        val response = ApiService().listProduct(requestProductList)
        println(response)
    }
}