USE [DatLichCatToc]
GO
/****** Object:  Table [dbo].[Ca_Lam]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ca_Lam](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_LichLam] [int] NULL,
	[TG_BD] [time](7) NULL,
	[TG_KT] [time](7) NULL,
	[TenCaLam] [nvarchar](50) NULL,
 CONSTRAINT [PK_Ca_Lam] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongViec]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongViec](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaCV] [nchar](10) NULL,
	[TenCV] [nvarchar](50) NULL,
	[MucLuong] [decimal](10, 2) NULL,
	[MoTa] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_CongViec] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dich_Vu]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dich_Vu](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ma_DV] [nchar](10) NULL,
	[Ten_DV] [nvarchar](50) NULL,
	[Anh] [nvarchar](50) NULL,
	[ThoiGianDV] [time](7) NULL,
	[GiaTien] [decimal](10, 2) NULL,
	[MoTa] [nvarchar](50) NULL,
	[ID_LoaiDV] [int] NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_Dich_Vu] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Giao_Ca]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Giao_Ca](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_CaLam] [int] NOT NULL,
	[ID_NV_Nhan] [int] NOT NULL,
	[Ngay_Ca_Lam] [date] NULL,
	[GhiChu] [nchar](10) NULL,
	[trangThai] [int] NULL,
	[Tien_Ca_Lam] [decimal](18, 0) NULL,
 CONSTRAINT [PK_Giao_Ca] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HDCT]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HDCT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_HD] [int] NULL,
	[ID_DV] [int] NULL,
	[So_Luong] [int] NULL,
	[Thanh_Tien] [decimal](10, 2) NULL,
	[GhiChu] [nvarchar](50) NULL,
 CONSTRAINT [PK_HDCT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Hoa_Don]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hoa_Don](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ma_HD] [nchar](10) NULL,
	[Ten_HD] [nvarchar](50) NULL,
	[NgayTao] [datetime] NULL,
	[NgaySua] [datetime] NULL,
	[TongTien] [decimal](10, 2) NULL,
	[MoTa] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
	[ID_NV] [int] NULL,
	[ID_KH] [int] NULL,
	[LichHenId] [int] NULL,
 CONSTRAINT [PK_Hoa_Don] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Khach_Hang]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khach_Hang](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ma_KH] [nvarchar](10) NULL,
	[Ten_KH] [nvarchar](50) NULL,
	[SDT_KH] [nchar](20) NULL,
	[GioiTinh] [int] NULL,
	[DiaChi] [nvarchar](100) NULL,
	[Email] [nvarchar](50) NULL,
	[NgaySinh] [date] NULL,
	[ID_Role] [int] NULL,
	[TrangThai] [int] NULL,
	[NgayTao] [datetime] NULL,
 CONSTRAINT [PK_Khach_Hang] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lich_Lam]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lich_Lam](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_NhanVien] [int] NULL,
	[NgayLamViec] [date] NULL,
	[GhiChu] [nvarchar](50) NULL,
	[TenCaLam] [nvarchar](20) NULL,
	[TG_BD] [time](7) NULL,
	[TG_KT] [time](7) NULL,
 CONSTRAINT [PK_Lich_Lam] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichHen]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichHen](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_KH] [int] NOT NULL,
	[ID_NV] [int] NOT NULL,
	[ID_DV] [int] NOT NULL,
	[Ma_LH] [nvarchar](10) NULL,
	[NgayHen] [date] NULL,
	[GioHen] [time](7) NULL,
	[TienCoc] [decimal](20, 2) NULL,
	[NgayTao] [datetime] NULL,
	[NgaySua] [datetime] NULL,
	[GhiChu] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_LichHen] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichSu_HD]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichSu_HD](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_HD] [int] NULL,
	[Nguoi_Sua] [nvarchar](50) NULL,
	[ThoiGian] [datetime] NULL,
	[ND_Sua] [nvarchar](50) NULL,
	[SoLanThayDoi] [int] NULL,
	[MoTa] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_LichSu_HD] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Loai_DV]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai_DV](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ma_LoaiDV] [nchar](10) NULL,
	[TenDV] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_Loai_DV] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NV_DV]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NV_DV](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_NV] [int] NULL,
	[ID_DV] [int] NULL,
 CONSTRAINT [PK_NV_DV] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Nhan_Vien]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Nhan_Vien](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ma_NV] [nchar](10) NULL,
	[Ten_NV] [nvarchar](50) NULL,
	[GioiTinh] [int] NULL,
	[Email] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[SDT] [nchar](20) NULL,
	[NgaySinh] [date] NULL,
	[ID_Role] [int] NULL,
	[ID_CV] [int] NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_Nhan_Vien] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tai_Khoan]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tai_Khoan](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[tenDangNhap] [nchar](50) NULL,
	[matKhau] [nchar](20) NULL,
	[ID_Role] [int] NULL,
	[TrangThai] [int] NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_Tai_Khoan] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tham_So]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tham_So](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaThamSo] [nchar](10) NULL,
	[TenThamSo] [nvarchar](50) NULL,
	[GT_HienTai] [nvarchar](225) NULL,
	[Ngay_Ap_Dung] [date] NULL,
	[Ngay_Het_Han] [date] NULL,
	[MoTa] [text] NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_Tham_So] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThanhToan]    Script Date: 8/4/2024 10:18:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThanhToan](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_HD] [int] NULL,
	[PhuongThuc] [int] NULL,
	[TrangThai] [int] NULL,
 CONSTRAINT [PK_ThanhToan] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Ca_Lam] ON 

INSERT [dbo].[Ca_Lam] ([ID], [ID_LichLam], [TG_BD], [TG_KT], [TenCaLam]) VALUES (1, 1, CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time), N'Ca sáng')
INSERT [dbo].[Ca_Lam] ([ID], [ID_LichLam], [TG_BD], [TG_KT], [TenCaLam]) VALUES (2, 2, CAST(N'11:00:00' AS Time), CAST(N'15:00:00' AS Time), N'Ca chiều')
INSERT [dbo].[Ca_Lam] ([ID], [ID_LichLam], [TG_BD], [TG_KT], [TenCaLam]) VALUES (3, 3, CAST(N'15:00:00' AS Time), CAST(N'20:00:00' AS Time), N'Ca tối')
SET IDENTITY_INSERT [dbo].[Ca_Lam] OFF
GO
SET IDENTITY_INSERT [dbo].[CongViec] ON 

INSERT [dbo].[CongViec] ([ID], [MaCV], [TenCV], [MucLuong], [MoTa], [TrangThai]) VALUES (1, N'CV01      ', N'Nhân viên cắt tóc', NULL, NULL, 1)
INSERT [dbo].[CongViec] ([ID], [MaCV], [TenCV], [MucLuong], [MoTa], [TrangThai]) VALUES (2, N'CV02      ', N'Nhân viên SPA', NULL, NULL, 1)
INSERT [dbo].[CongViec] ([ID], [MaCV], [TenCV], [MucLuong], [MoTa], [TrangThai]) VALUES (3, N'CV03      ', N'Nhân viên Mát Xa', NULL, NULL, 1)
INSERT [dbo].[CongViec] ([ID], [MaCV], [TenCV], [MucLuong], [MoTa], [TrangThai]) VALUES (4, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[CongViec] OFF
GO
SET IDENTITY_INSERT [dbo].[Dich_Vu] ON 

INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (1, N'DV001     ', N'Cắt Tóc Nam', NULL, CAST(N'00:30:00' AS Time), CAST(50.00 AS Decimal(10, 2)), N'Khá hay', 3, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (2, N'DV002     ', N'Cắt Tóc Nữ', N'Save.png', CAST(N'00:30:00' AS Time), CAST(80.00 AS Decimal(10, 2)), N'Khá tệ', 2, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (3, N'DV003     ', N'Nặn mụn', N'eye.png', CAST(N'00:30:00' AS Time), CAST(100.00 AS Decimal(10, 2)), N'Non', 3, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (4, N'DV004     ', N'Tẩy nốt rồi', N'fpt.png', CAST(N'00:30:00' AS Time), CAST(20.00 AS Decimal(10, 2)), N'Gà', 4, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (5, N'DV005     ', N'Xỏ Khuyên', N'add_user', CAST(N'00:10:00' AS Time), CAST(15.00 AS Decimal(10, 2)), N'Muốn mấy điểm', 5, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (6, N'DV006     ', N'Gội đầu Nam', NULL, CAST(N'00:15:00' AS Time), CAST(10.00 AS Decimal(10, 2)), N'qua môn', 6, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (7, N'DV007     ', N'SDưere', NULL, CAST(N'00:10:00' AS Time), CAST(999.00 AS Decimal(10, 2)), N'DS', 2, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (8, N'DV008     ', N'Gội đầu nữ', NULL, CAST(N'00:30:00' AS Time), CAST(30.00 AS Decimal(10, 2)), N'ok', 3, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (9, N'DV0013    ', N'Hút mụn', NULL, CAST(N'00:10:00' AS Time), CAST(99.00 AS Decimal(10, 2)), N'Mịn vãi', 1, 0)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (10, N'DV0010    ', N'Hết mụn', N'Add.png', CAST(N'00:30:00' AS Time), CAST(199.00 AS Decimal(10, 2)), N'chưa biết', 1, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (11, N'DV007     ', N'SDưere', NULL, CAST(N'00:10:00' AS Time), CAST(999.00 AS Decimal(10, 2)), N'DS', 2, 1)
INSERT [dbo].[Dich_Vu] ([ID], [Ma_DV], [Ten_DV], [Anh], [ThoiGianDV], [GiaTien], [MoTa], [ID_LoaiDV], [TrangThai]) VALUES (17, N'KH3b4b003 ', N'Cắt ngắn', NULL, CAST(N'00:30:00' AS Time), CAST(100000.00 AS Decimal(10, 2)), N'alo', 2, 1)
SET IDENTITY_INSERT [dbo].[Dich_Vu] OFF
GO
SET IDENTITY_INSERT [dbo].[Giao_Ca] ON 

INSERT [dbo].[Giao_Ca] ([ID], [ID_CaLam], [ID_NV_Nhan], [Ngay_Ca_Lam], [GhiChu], [trangThai], [Tien_Ca_Lam]) VALUES (2, 2, 1, CAST(N'2024-01-04' AS Date), N'Đã làm    ', 1, CAST(120000 AS Decimal(18, 0)))
INSERT [dbo].[Giao_Ca] ([ID], [ID_CaLam], [ID_NV_Nhan], [Ngay_Ca_Lam], [GhiChu], [trangThai], [Tien_Ca_Lam]) VALUES (3, 1, 2, CAST(N'2024-05-04' AS Date), N'Đã làm khá', 1, CAST(120000 AS Decimal(18, 0)))
INSERT [dbo].[Giao_Ca] ([ID], [ID_CaLam], [ID_NV_Nhan], [Ngay_Ca_Lam], [GhiChu], [trangThai], [Tien_Ca_Lam]) VALUES (4, 3, 1, CAST(N'2024-04-04' AS Date), N'Đã làm tốt', 1, CAST(240000 AS Decimal(18, 0)))
INSERT [dbo].[Giao_Ca] ([ID], [ID_CaLam], [ID_NV_Nhan], [Ngay_Ca_Lam], [GhiChu], [trangThai], [Tien_Ca_Lam]) VALUES (5, 2, 3, CAST(N'2024-04-05' AS Date), N'Ok        ', 1, CAST(200000 AS Decimal(18, 0)))
INSERT [dbo].[Giao_Ca] ([ID], [ID_CaLam], [ID_NV_Nhan], [Ngay_Ca_Lam], [GhiChu], [trangThai], [Tien_Ca_Lam]) VALUES (6, 1, 10, CAST(N'2024-08-03' AS Date), N'abc       ', 0, CAST(10000 AS Decimal(18, 0)))
SET IDENTITY_INSERT [dbo].[Giao_Ca] OFF
GO
SET IDENTITY_INSERT [dbo].[HDCT] ON 

INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (1, 1, 1, 1, CAST(50.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (2, 1, 2, 1, CAST(80.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (3, 2, 1, 1, CAST(50.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (4, 2, 3, 1, CAST(100.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (5, 2, 4, 1, CAST(20.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (6, 3, 1, 1, CAST(50.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (7, 3, 2, 2, CAST(160.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (8, 4, 1, 1, CAST(50.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (9, 4, 4, 1, CAST(20.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (10, 9, 1, 1, CAST(50.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (11, 9, 2, 1, CAST(80.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (12, 9, 17, 1, CAST(100000.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (13, 19, 2, 2, CAST(160.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (14, 19, 3, 1, CAST(100.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (15, 19, 8, 1, CAST(30.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (16, 19, 4, 1, CAST(20.00 AS Decimal(10, 2)), NULL)
INSERT [dbo].[HDCT] ([ID], [ID_HD], [ID_DV], [So_Luong], [Thanh_Tien], [GhiChu]) VALUES (17, 19, 6, 1, CAST(10.00 AS Decimal(10, 2)), NULL)
SET IDENTITY_INSERT [dbo].[HDCT] OFF
GO
SET IDENTITY_INSERT [dbo].[Hoa_Don] ON 

INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (1, N'HD001     ', NULL, CAST(N'2024-02-24T00:00:00.000' AS DateTime), NULL, CAST(130.00 AS Decimal(10, 2)), NULL, 1, 1, 1, 1)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (2, N'HD002     ', NULL, CAST(N'2024-07-22T00:00:00.000' AS DateTime), NULL, CAST(170.00 AS Decimal(10, 2)), NULL, 1, 2, 2, 2)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (3, N'HD003     ', NULL, CAST(N'2024-07-21T00:00:00.000' AS DateTime), NULL, CAST(210.00 AS Decimal(10, 2)), NULL, 0, 3, 3, 3)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (4, N'HD004     ', NULL, CAST(N'2024-07-23T00:00:00.000' AS DateTime), NULL, CAST(70.00 AS Decimal(10, 2)), NULL, 0, 4, 4, 4)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (5, N'HD30376d7 ', NULL, CAST(N'2024-08-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, 0, 3, 2, 10)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (6, N'HDffdc057 ', NULL, CAST(N'2024-08-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, 0, 2, 3, 11)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (7, N'HDab0eed4 ', NULL, CAST(N'2024-08-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, 0, 4, 2, 12)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (8, N'HD821da80 ', NULL, CAST(N'2024-08-03T00:00:00.000' AS DateTime), NULL, NULL, NULL, 0, 1, 3, 13)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (9, N'HDc07a411 ', NULL, CAST(N'2024-08-03T00:00:00.000' AS DateTime), NULL, CAST(100130.00 AS Decimal(10, 2)), NULL, 0, 2, 6, 14)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (17, N'11        ', NULL, CAST(N'2024-08-03T00:00:00.000' AS DateTime), NULL, NULL, NULL, 1, 11, 5, NULL)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (18, N'HDf786a8e ', NULL, CAST(N'2024-08-03T00:00:00.000' AS DateTime), NULL, NULL, NULL, 0, NULL, NULL, 15)
INSERT [dbo].[Hoa_Don] ([ID], [Ma_HD], [Ten_HD], [NgayTao], [NgaySua], [TongTien], [MoTa], [TrangThai], [ID_NV], [ID_KH], [LichHenId]) VALUES (19, N'HD277d354 ', NULL, CAST(N'2024-08-03T00:00:00.000' AS DateTime), NULL, CAST(320.00 AS Decimal(10, 2)), NULL, 0, 3, 5, 16)
SET IDENTITY_INSERT [dbo].[Hoa_Don] OFF
GO
SET IDENTITY_INSERT [dbo].[Khach_Hang] ON 

INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (1, N'KH3747015', N'Nguyễn Đức Mạnh', N'0123456789          ', 1, N'Tây Hồ', N'anguyenVan12@gmail.com', CAST(N'2002-12-12' AS Date), 1, 1, CAST(N'2024-07-01T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (2, N'KH3738017', N'Nguyễn Đức Thành', N'0987763253          ', 1, N'Hoàng Mai', N'thanhnd@gmail.com', CAST(N'2006-06-06' AS Date), 1, 1, CAST(N'2024-07-05T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (3, N'KH3734916', N'Cao Thị C', N'0237373673          ', 0, N'Đống Đa', N'cc@gmail.com', CAST(N'2004-01-01' AS Date), 1, 1, CAST(N'2024-07-31T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (4, N'KH3784018', N'Nguyễn Đức Bảo', N'0982362368          ', 1, N'Hưng Yên', N'dd@gmail.com', CAST(N'2005-09-09' AS Date), 1, 1, CAST(N'2024-07-31T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (5, N'KH9684cc3', N'Phạm Thị Ngọc', N'0987654321          ', 0, N'Bạch Sam', N'nocc05@gmail.com', CAST(N'2005-05-11' AS Date), 1, 1, CAST(N'2024-08-01T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (6, N'KH0099879', N'Phạm Thị Hồng Ngọc', N'0987763253          ', 0, N'Hoàng Mai', N'phamthihongngoc123@gmail.com', CAST(N'2003-06-06' AS Date), 1, 1, CAST(N'2024-08-01T00:00:00.000' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (7, N'KH1c88fdc', N'Phạm Ngọc', N'0987763253          ', 0, N'Hoàng Mai', N'BHoangThi123@gmail.com', CAST(N'2003-06-06' AS Date), 1, 1, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (8, N'KH1c8e9dd', N'Phạm Thị Ngọc Bích', N'0987763253          ', 0, N'Hoài Đức', N'ctp123@gmail.com', CAST(N'2001-06-11' AS Date), 1, 1, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (9, N'KH004', N'Nguyễn Đức Bảo', N'0982362368          ', 1, N'TP Hưng Yên', N'dd@gmail.com', CAST(N'2005-09-09' AS Date), 1, 0, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (10, N'KH0010', N'Nguyễn Đức Đoàn', N'0987654321          ', 1, N'Xuân Dục', N'doanng@gmail.com', CAST(N'2009-01-02' AS Date), 1, 1, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (11, N'KH0aiIuE0a', N'Đức Đạt', N'0987654211          ', 1, N'Hưng Yên', N'datdz2442005@gmail.com', CAST(N'2004-04-24' AS Date), 1, 0, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (14, N'KH3734015', N'Nguyễn Đức Dũng', N'0982362367          ', 1, N'TP Hưng Yên', N'dd@gmail.com', CAST(N'2005-09-09' AS Date), 1, 1, NULL)
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (15, N'KH9664cc3', N'Đức Đạt', N'0987654321          ', 1, N'Hưng YÊn', N'dd@gmail.com', CAST(N'2005-04-24' AS Date), 1, 1, CAST(N'2024-08-01T01:20:39.430' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (16, N'KH0099879', N'Phạm Thị Ngọc', N'0987654321          ', 0, N'Bạch Sam', N'nocc05@gmail.com', CAST(N'2005-05-11' AS Date), 1, 0, CAST(N'2024-08-01T01:31:12.573' AS DateTime))
INSERT [dbo].[Khach_Hang] ([ID], [Ma_KH], [Ten_KH], [SDT_KH], [GioiTinh], [DiaChi], [Email], [NgaySinh], [ID_Role], [TrangThai], [NgayTao]) VALUES (17, N'KH1c8efdd', N'Phan Ngọc hiếu', N'0198273654          ', 1, N'Xuân Dục', N'hieu123@gmail.com', CAST(N'2024-08-08' AS Date), 1, 0, CAST(N'2024-08-01T01:40:26.573' AS DateTime))
SET IDENTITY_INSERT [dbo].[Khach_Hang] OFF
GO
SET IDENTITY_INSERT [dbo].[Lich_Lam] ON 

INSERT [dbo].[Lich_Lam] ([ID], [ID_NhanVien], [NgayLamViec], [GhiChu], [TenCaLam], [TG_BD], [TG_KT]) VALUES (1, 1, CAST(N'0030-01-14' AS Date), N'sf', N'Ca Chiều', CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time))
INSERT [dbo].[Lich_Lam] ([ID], [ID_NhanVien], [NgayLamViec], [GhiChu], [TenCaLam], [TG_BD], [TG_KT]) VALUES (2, 2, CAST(N'2024-07-24' AS Date), N'sf', N'Ca Sáng', CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time))
INSERT [dbo].[Lich_Lam] ([ID], [ID_NhanVien], [NgayLamViec], [GhiChu], [TenCaLam], [TG_BD], [TG_KT]) VALUES (3, 3, CAST(N'0031-01-14' AS Date), N'phế', N'Ca Chiều', CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time))
INSERT [dbo].[Lich_Lam] ([ID], [ID_NhanVien], [NgayLamViec], [GhiChu], [TenCaLam], [TG_BD], [TG_KT]) VALUES (4, 1, CAST(N'0014-01-31' AS Date), N'phế', N'Ca Sáng', CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time))
INSERT [dbo].[Lich_Lam] ([ID], [ID_NhanVien], [NgayLamViec], [GhiChu], [TenCaLam], [TG_BD], [TG_KT]) VALUES (5, 7, CAST(N'0031-01-14' AS Date), N'lú vãi', N'Ca Sáng', CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time))
SET IDENTITY_INSERT [dbo].[Lich_Lam] OFF
GO
SET IDENTITY_INSERT [dbo].[LichHen] ON 

INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (1, 1, 2, 1, N'MALH001', CAST(N'2024-08-01' AS Date), CAST(N'17:00:00' AS Time), CAST(30000.00 AS Decimal(20, 2)), CAST(N'2024-07-24T16:00:00.000' AS DateTime), NULL, N'Phê', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (2, 2, 3, 2, N'MALH002', CAST(N'2024-08-01' AS Date), CAST(N'11:00:00' AS Time), CAST(30000.00 AS Decimal(20, 2)), CAST(N'2024-07-31T07:00:00.000' AS DateTime), NULL, N'Dã man', 0)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (3, 3, 1, 3, N'MALH003', CAST(N'2024-07-25' AS Date), CAST(N'07:15:00' AS Time), CAST(30000.00 AS Decimal(20, 2)), CAST(N'2024-07-24T00:00:00.000' AS DateTime), NULL, N'Khách hàng thích là được', 2)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (4, 6, 3, 6, N'MALH004', CAST(N'2024-08-15' AS Date), CAST(N'09:00:00' AS Time), NULL, NULL, NULL, N'Làm tóc theo ki?u m?i', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (5, 5, 3, 1, NULL, CAST(N'2024-07-31' AS Date), CAST(N'12:00:00' AS Time), NULL, NULL, NULL, N'', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (6, 6, 1, 1, NULL, CAST(N'2024-07-31' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'abc', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (7, 3, 1, 1, NULL, CAST(N'2024-07-31' AS Date), CAST(N'09:00:00' AS Time), NULL, NULL, NULL, N'xyz', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (8, 4, 1, 1, NULL, CAST(N'2024-07-31' AS Date), CAST(N'12:30:00' AS Time), NULL, NULL, NULL, N'vbasdf', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (9, 11, 1, 1, NULL, CAST(N'2024-08-01' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'day', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (10, 1, 1, 1, NULL, CAST(N'2024-08-01' AS Date), CAST(N'10:30:00' AS Time), NULL, NULL, NULL, N'11', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (11, 11, 1, 1, NULL, CAST(N'2024-08-02' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'abc', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (12, 5, 1, 1, NULL, CAST(N'2024-08-02' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'bfd', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (13, 4, 4, 1, NULL, CAST(N'2024-08-08' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (14, 11, 5, 1, NULL, CAST(N'2024-08-03' AS Date), CAST(N'09:30:00' AS Time), NULL, NULL, NULL, N'', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (15, 17, 1, 1, NULL, CAST(N'2024-08-03' AS Date), CAST(N'08:00:00' AS Time), NULL, NULL, NULL, N'', 1)
INSERT [dbo].[LichHen] ([ID], [ID_KH], [ID_NV], [ID_DV], [Ma_LH], [NgayHen], [GioHen], [TienCoc], [NgayTao], [NgaySua], [GhiChu], [TrangThai]) VALUES (16, 5, 3, 2, NULL, CAST(N'2024-08-03' AS Date), CAST(N'09:30:00' AS Time), NULL, NULL, NULL, N'', 1)
SET IDENTITY_INSERT [dbo].[LichHen] OFF
GO
SET IDENTITY_INSERT [dbo].[Loai_DV] ON 

INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (1, N'LDV01     ', N'Chăm sóc da', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (2, N'LDV02     ', N'Cắt', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (3, N'LDV03     ', N'Combo', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (4, N'LDV04     ', N'Thư Giãn', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (5, N'LDV05     ', N'Nhuộm', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (6, N'LDV06     ', N'Khác', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (7, N'LDV07     ', N'Đặt bừa', 1)
INSERT [dbo].[Loai_DV] ([ID], [Ma_LoaiDV], [TenDV], [TrangThai]) VALUES (9, N'KH64f8312 ', N'hai', 1)
SET IDENTITY_INSERT [dbo].[Loai_DV] OFF
GO
SET IDENTITY_INSERT [dbo].[Nhan_Vien] ON 

INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (1, N'NV001     ', N'Giang A Phò', 1, N'batBai123@gmail.com', N'Hà Giang', N'0988554223          ', CAST(N'2002-12-13' AS Date), 2, 1, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (2, N'NV002     ', N'Voòng Cắm Sìn', 0, N'anhhung345@gmail.com', N'Lào Cai', N'0285334578          ', CAST(N'2003-12-14' AS Date), 2, 2, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (3, N'NV003     ', N'Chíu Chăn Nàm', 1, N'joker123@gmail.com', N'Cao Bằng', N'0300112223          ', CAST(N'2004-09-02' AS Date), 2, 1, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (4, N'NV004     ', N'Phú A Nhì', 1, N'kiemhiep09@gmail.com', N'Bắc Kạn', N'0399887788          ', CAST(N'2005-10-01' AS Date), 2, 3, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (5, N'CV02      ', N'Nọc Nọc', 0, N'anhhung345@gmail.com', N'Hưng Yên', N'0285334588          ', CAST(N'2004-08-15' AS Date), NULL, 2, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (6, N'CV02      ', N'Nọc Nọc', 0, N'anhhung345@gmail.com', N'Hưng Yên', N'0285334588          ', CAST(N'2004-08-15' AS Date), NULL, 2, 0)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (7, N'NV007     ', N'Đạt', 1, N'datdz2442005@gmail.com', N'VN', N'0987542333          ', NULL, NULL, 1, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (8, N'NV005     ', N'Nọc Nọc', 0, N'anhhung345@gmail.com', N'Hưng Yên', N'0285334588          ', NULL, NULL, 2, 1)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (9, N'NV008     ', N'Đạt', 1, N'datdz2442005@gmail.com', N'VN', N'0987542333          ', NULL, NULL, 1, 0)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (10, N'NV009     ', N'Phú A Nhì a', 0, N'kiemhiep09@gmail.com', N'Bắc Kạn', N'0399887788          ', NULL, NULL, 3, 0)
INSERT [dbo].[Nhan_Vien] ([ID], [Ma_NV], [Ten_NV], [GioiTinh], [Email], [DiaChi], [SDT], [NgaySinh], [ID_Role], [ID_CV], [TrangThai]) VALUES (11, N'Nadada    ', N'Đức', 1, N'đ@gmail.com', N'Vn', N'0987654321          ', CAST(N'2024-08-05' AS Date), 2, 3, 1)
SET IDENTITY_INSERT [dbo].[Nhan_Vien] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([ID], [Ten]) VALUES (1, N'Khách Hàng')
INSERT [dbo].[Role] ([ID], [Ten]) VALUES (2, N'Nhân Viên')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Tai_Khoan] ON 

INSERT [dbo].[Tai_Khoan] ([ID], [tenDangNhap], [matKhau], [ID_Role], [TrangThai], [Email]) VALUES (1, N'datzoe                                            ', N'123                 ', 1, NULL, N'datdz2442005@gmail.com')
INSERT [dbo].[Tai_Khoan] ([ID], [tenDangNhap], [matKhau], [ID_Role], [TrangThai], [Email]) VALUES (2, N'abc                                               ', N'12345               ', 2, NULL, N'xuanhaiff123@gmail.com')
SET IDENTITY_INSERT [dbo].[Tai_Khoan] OFF
GO
/****** Object:  Index [unique_lichhenid]    Script Date: 8/4/2024 10:18:28 AM ******/
ALTER TABLE [dbo].[Hoa_Don] ADD  CONSTRAINT [unique_lichhenid] UNIQUE NONCLUSTERED 
(
	[LichHenId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Khach_Hang] ADD  DEFAULT (getdate()) FOR [NgayTao]
GO
ALTER TABLE [dbo].[Ca_Lam]  WITH CHECK ADD  CONSTRAINT [FK_Ca_Lam_Lich_Lam] FOREIGN KEY([ID_LichLam])
REFERENCES [dbo].[Lich_Lam] ([ID])
GO
ALTER TABLE [dbo].[Ca_Lam] CHECK CONSTRAINT [FK_Ca_Lam_Lich_Lam]
GO
ALTER TABLE [dbo].[Dich_Vu]  WITH CHECK ADD  CONSTRAINT [FK_Dich_Vu_Loai_DV] FOREIGN KEY([ID_LoaiDV])
REFERENCES [dbo].[Loai_DV] ([ID])
GO
ALTER TABLE [dbo].[Dich_Vu] CHECK CONSTRAINT [FK_Dich_Vu_Loai_DV]
GO
ALTER TABLE [dbo].[Giao_Ca]  WITH CHECK ADD  CONSTRAINT [FK_Giao_Ca_Ca_Lam] FOREIGN KEY([ID_CaLam])
REFERENCES [dbo].[Ca_Lam] ([ID])
GO
ALTER TABLE [dbo].[Giao_Ca] CHECK CONSTRAINT [FK_Giao_Ca_Ca_Lam]
GO
ALTER TABLE [dbo].[Giao_Ca]  WITH CHECK ADD  CONSTRAINT [FK_Giao_Ca_Nhan_Vien] FOREIGN KEY([ID_NV_Nhan])
REFERENCES [dbo].[Nhan_Vien] ([ID])
GO
ALTER TABLE [dbo].[Giao_Ca] CHECK CONSTRAINT [FK_Giao_Ca_Nhan_Vien]
GO
ALTER TABLE [dbo].[HDCT]  WITH CHECK ADD  CONSTRAINT [FK_HDCT_Dich_Vu] FOREIGN KEY([ID_DV])
REFERENCES [dbo].[Dich_Vu] ([ID])
GO
ALTER TABLE [dbo].[HDCT] CHECK CONSTRAINT [FK_HDCT_Dich_Vu]
GO
ALTER TABLE [dbo].[HDCT]  WITH CHECK ADD  CONSTRAINT [FK_HDCT_Hoa_Don] FOREIGN KEY([ID_HD])
REFERENCES [dbo].[Hoa_Don] ([ID])
GO
ALTER TABLE [dbo].[HDCT] CHECK CONSTRAINT [FK_HDCT_Hoa_Don]
GO
ALTER TABLE [dbo].[Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_Hoa_Don_Khach_Hang] FOREIGN KEY([ID_KH])
REFERENCES [dbo].[Khach_Hang] ([ID])
GO
ALTER TABLE [dbo].[Hoa_Don] CHECK CONSTRAINT [FK_Hoa_Don_Khach_Hang]
GO
ALTER TABLE [dbo].[Hoa_Don]  WITH CHECK ADD  CONSTRAINT [FK_Hoa_Don_Nhan_Vien] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[Nhan_Vien] ([ID])
GO
ALTER TABLE [dbo].[Hoa_Don] CHECK CONSTRAINT [FK_Hoa_Don_Nhan_Vien]
GO
ALTER TABLE [dbo].[Hoa_Don]  WITH CHECK ADD  CONSTRAINT [fk_lichhenid] FOREIGN KEY([LichHenId])
REFERENCES [dbo].[LichHen] ([ID])
GO
ALTER TABLE [dbo].[Hoa_Don] CHECK CONSTRAINT [fk_lichhenid]
GO
ALTER TABLE [dbo].[Khach_Hang]  WITH CHECK ADD  CONSTRAINT [FK_Khach_Hang_Role] FOREIGN KEY([ID_Role])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[Khach_Hang] CHECK CONSTRAINT [FK_Khach_Hang_Role]
GO
ALTER TABLE [dbo].[Lich_Lam]  WITH CHECK ADD  CONSTRAINT [FK_Lich_Lam_Nhan_Vien] FOREIGN KEY([ID_NhanVien])
REFERENCES [dbo].[Nhan_Vien] ([ID])
GO
ALTER TABLE [dbo].[Lich_Lam] CHECK CONSTRAINT [FK_Lich_Lam_Nhan_Vien]
GO
ALTER TABLE [dbo].[LichHen]  WITH CHECK ADD  CONSTRAINT [FK_LichHen_Dich_Vu] FOREIGN KEY([ID_DV])
REFERENCES [dbo].[Dich_Vu] ([ID])
GO
ALTER TABLE [dbo].[LichHen] CHECK CONSTRAINT [FK_LichHen_Dich_Vu]
GO
ALTER TABLE [dbo].[LichHen]  WITH CHECK ADD  CONSTRAINT [FK_LichHen_Khach_Hang] FOREIGN KEY([ID_KH])
REFERENCES [dbo].[Khach_Hang] ([ID])
GO
ALTER TABLE [dbo].[LichHen] CHECK CONSTRAINT [FK_LichHen_Khach_Hang]
GO
ALTER TABLE [dbo].[LichHen]  WITH CHECK ADD  CONSTRAINT [FK_LichHen_Nhan_Vien] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[Nhan_Vien] ([ID])
GO
ALTER TABLE [dbo].[LichHen] CHECK CONSTRAINT [FK_LichHen_Nhan_Vien]
GO
ALTER TABLE [dbo].[LichSu_HD]  WITH CHECK ADD  CONSTRAINT [FK_LichSu_HD_Hoa_Don] FOREIGN KEY([ID_HD])
REFERENCES [dbo].[Hoa_Don] ([ID])
GO
ALTER TABLE [dbo].[LichSu_HD] CHECK CONSTRAINT [FK_LichSu_HD_Hoa_Don]
GO
ALTER TABLE [dbo].[NV_DV]  WITH CHECK ADD  CONSTRAINT [FK_NV_DV_Dich_Vu] FOREIGN KEY([ID_DV])
REFERENCES [dbo].[Dich_Vu] ([ID])
GO
ALTER TABLE [dbo].[NV_DV] CHECK CONSTRAINT [FK_NV_DV_Dich_Vu]
GO
ALTER TABLE [dbo].[NV_DV]  WITH CHECK ADD  CONSTRAINT [FK_NV_DV_Nhan_Vien] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[Nhan_Vien] ([ID])
GO
ALTER TABLE [dbo].[NV_DV] CHECK CONSTRAINT [FK_NV_DV_Nhan_Vien]
GO
ALTER TABLE [dbo].[Nhan_Vien]  WITH CHECK ADD  CONSTRAINT [FK_Nhan_Vien_CongViec] FOREIGN KEY([ID_CV])
REFERENCES [dbo].[CongViec] ([ID])
GO
ALTER TABLE [dbo].[Nhan_Vien] CHECK CONSTRAINT [FK_Nhan_Vien_CongViec]
GO
ALTER TABLE [dbo].[Nhan_Vien]  WITH CHECK ADD  CONSTRAINT [FK_Nhan_Vien_Role] FOREIGN KEY([ID_Role])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[Nhan_Vien] CHECK CONSTRAINT [FK_Nhan_Vien_Role]
GO
ALTER TABLE [dbo].[Tai_Khoan]  WITH CHECK ADD  CONSTRAINT [FK_Tai_Khoan_Role] FOREIGN KEY([ID_Role])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[Tai_Khoan] CHECK CONSTRAINT [FK_Tai_Khoan_Role]
GO
ALTER TABLE [dbo].[ThanhToan]  WITH CHECK ADD  CONSTRAINT [FK_ThanhToan_Hoa_Don] FOREIGN KEY([ID_HD])
REFERENCES [dbo].[Hoa_Don] ([ID])
GO
ALTER TABLE [dbo].[ThanhToan] CHECK CONSTRAINT [FK_ThanhToan_Hoa_Don]
GO
