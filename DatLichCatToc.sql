USE [DatLichCatToc]
GO
/****** Object:  Table [dbo].[Ca_Lam]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[CongViec]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Dich_Vu]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[DVTam]    Script Date: 3/6/2025 8:05:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DVTam](
	[id] [int] NULL,
	[ten] [nvarchar](50) NULL,
	[thoiGian] [nvarchar](50) NULL,
	[giaTien] [decimal](10, 2) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Giao_Ca]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[HDCT]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Hoa_Don]    Script Date: 3/6/2025 8:05:26 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [unique_lichhenid] UNIQUE NONCLUSTERED 
(
	[LichHenId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Khach_Hang]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Lich_Lam]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[LichHen]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[LichSu_HD]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Loai_DV]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[NV_DV]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Nhan_Vien]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Tai_Khoan]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[Tham_So]    Script Date: 3/6/2025 8:05:26 PM ******/
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
/****** Object:  Table [dbo].[ThanhToan]    Script Date: 3/6/2025 8:05:26 PM ******/
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
